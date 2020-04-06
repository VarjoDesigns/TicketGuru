package fi.rbmk.ticketguru.eventOrganizer;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.rbmk.ticketguru.postcode.*;
import fi.rbmk.ticketguru.event.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/eventOrganizers", produces = "application/hal+json")
public class EventOrganizerController {

    @Autowired EventOrganizerRepository eoRepository;
    @Autowired PostcodeRepository pRepository;
    @Autowired EventRepository eRepository;

    @PostMapping(produces = "application/hal+json")
    ResponseEntity<?> add(@Valid @RequestBody EventOrganizer eventOrganizer) {
        try {
            EventOrganizerLinks links = new EventOrganizerLinks(eventOrganizer);
            eventOrganizer.add(links.getAll());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Duplicate entry");
        }
        Resource<EventOrganizer> resource = new Resource<EventOrganizer>(eventOrganizer);
        return ResponseEntity.ok(resource);
    }

    @PatchMapping(value = "/{id}", produces = "application/hal+json")
    ResponseEntity<EventOrganizer> edit(@Valid @RequestBody EventOrganizer newEventOrganizer, @PathVariable Long id) {
        EventOrganizer eventOrganizer = eoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        if(newEventOrganizer.getName() != "") { eventOrganizer.setName(newEventOrganizer.getName()); }
        if(newEventOrganizer.getStreetAddress() != "") { eventOrganizer.setStreetAddress(newEventOrganizer.getStreetAddress()); }
        if(newEventOrganizer.getPostcode() != null) { eventOrganizer.setPostcode(newEventOrganizer.getPostcode()); }
        if(newEventOrganizer.getTel() != "") { eventOrganizer.setTel(newEventOrganizer.getTel()); }
        if(newEventOrganizer.getEmail() != "") { eventOrganizer.setEmail(newEventOrganizer.getEmail()); }
        if(newEventOrganizer.getWWW() != "") { eventOrganizer.setWWW(newEventOrganizer.getWWW()); }
        if(newEventOrganizer.getContactPerson() != "") { eventOrganizer.setContactPerson(newEventOrganizer.getContactPerson()); }
        eoRepository.save(eventOrganizer);
        return ResponseEntity.created(URI.create("/api/eventOrganizers/" + eventOrganizer.getEventOrganizer_ID())).build();
    }

    @DeleteMapping(value = "/{id}", produces = "application/hal+json")
    ResponseEntity<?> delete(@PathVariable Long id) {
        EventOrganizer eventOrganizer = eoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        eventOrganizer.setInvalid();
        eoRepository.save(eventOrganizer);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(produces = "application/hal+json")
    ResponseEntity<Resources<EventOrganizer>> all() {
        List<EventOrganizer> eventOrganizers = eoRepository.findAll();
        Link link = linkTo(EventOrganizerController.class).withSelfRel();
        if (eventOrganizers.size() != 0) {
            for (EventOrganizer eventOrganizer : eventOrganizers) {
                EventOrganizerLinks links = new EventOrganizerLinks(eventOrganizer);
                eventOrganizer.add(links.getAll());
            }
            Resources<EventOrganizer> resources = new Resources<EventOrganizer>(eventOrganizers, link);
            return ResponseEntity.ok(resources);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/{id}", produces = "application/hal+json")
    public ResponseEntity<Resource<EventOrganizer>> one(@PathVariable Long id) {
        EventOrganizer eventOrganizer = eoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        EventOrganizerLinks links = new EventOrganizerLinks(eventOrganizer);
        eventOrganizer.add(links.getAll());
        Resource<EventOrganizer> resource = new Resource<EventOrganizer>(eventOrganizer);
        return ResponseEntity.ok(resource);
    }

    @GetMapping(value = "/{id}/postcode", produces = "application/hal+json")
    public ResponseEntity<Resource<Postcode>> getPostcode(@PathVariable Long id) {
        EventOrganizer eventOrganizer = eoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        Link link = linkTo(EventOrganizerController.class).withSelfRel();
        Postcode postcode = eventOrganizer.getPostcode();
        PostcodeLinks links = new PostcodeLinks(postcode);
        postcode.add(links.getAll());
        Resource<Postcode> resource = new Resource<Postcode>(postcode, link);
        return ResponseEntity.ok(resource);
    }

    @GetMapping(value = "/{id}/events", produces = "application/hal+json")
    public ResponseEntity<Resources<Event>> getEvents(@PathVariable Long id) {
        EventOrganizer eventOrganizer = eoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        Link link = linkTo(EventOrganizerController.class).withSelfRel();
        List<Event> events = eventOrganizer.getEvents();
        if (events.size() != 0) {
            for (Event event : events) {
                EventLinks links = new EventLinks(event);
                event.add(links.getAll());
            }
            Resources<Event> resources = new Resources<Event>(events, link);
            return ResponseEntity.ok(resources);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}