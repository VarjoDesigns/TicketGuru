package fi.rbmk.ticketguru.eventType;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.rbmk.ticketguru.event.*;

@RestController
@RequestMapping(value = "/api/eventTypes", produces = "application/hal+json")
public class EventTypeController {

    @Autowired EventTypeRepository etRepository;
    @Autowired EventRepository eRepository;

    @PostMapping(produces = "application/hal+json")
    ResponseEntity<?> add(@Valid @RequestBody EventType newEventType) {
        try {
            EventType eventType = etRepository.save(newEventType);
            EventTypeLinks links = new EventTypeLinks(eventType);
            eventType.add(links.getAll());
            Resource<EventType> resource = new Resource<EventType>(eventType);
            return ResponseEntity.ok(resource);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Duplicate entry");
        }
    }

    @PatchMapping(value = "/{id}", produces = "application/hal+json")
    ResponseEntity<EventType> edit(@Valid @RequestBody EventType newEventType, @PathVariable Long id) {
        EventType eventType = etRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        if(newEventType.getName() != "") { eventType.setName(newEventType.getName()); }
        if(newEventType.getInfo() != "") { eventType.setInfo(newEventType.getInfo()); }
        etRepository.save(eventType);
        return ResponseEntity.created(URI.create("/" + eventType.getEventType_ID())).build();
    }

    @DeleteMapping(value = "/{id}", produces = "application/hal+json")
    ResponseEntity<?> delete(@PathVariable Long id) {
        return etRepository.findById(id).map(m -> {
            etRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
    }

    @GetMapping(produces = "application/hal+json")
    ResponseEntity<Resources<EventType>> all() {
        List<EventType> eventTypes = etRepository.findAll();
        Link link = linkTo(EventTypeController.class).withSelfRel();
        if (eventTypes.size() != 0) {
            for (EventType eventType : eventTypes) {
                EventTypeLinks links = new EventTypeLinks(eventType);
                eventType.add(links.getAll());
            }
            Resources<EventType> resources = new Resources<EventType>(eventTypes, link);
            return ResponseEntity.ok(resources);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/{id}", produces = "application/hal+json")
    public ResponseEntity<Resource<EventType>> one(@PathVariable Long id) {
        EventType eventType = etRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        EventTypeLinks links = new EventTypeLinks(eventType);
        eventType.add(links.getAll());
        Resource<EventType> resource = new Resource<EventType>(eventType);
        return ResponseEntity.ok(resource);
    }

    @GetMapping(value = "/{id}/events", produces = "application/hal+json")
    public ResponseEntity<Resources<Event>> getEvents(@PathVariable Long id) {
        EventType eventType = etRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        Link link = linkTo(EventTypeController.class).withSelfRel();
        List<Event> events = eventType.getEvents();
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