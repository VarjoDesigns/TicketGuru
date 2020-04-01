package fi.rbmk.ticketguru.event;

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

import fi.rbmk.ticketguru.eventType.*;
import fi.rbmk.ticketguru.eventOrganizer.*;
import fi.rbmk.ticketguru.eventTicket.*;
import fi.rbmk.ticketguru.venue.*;
import fi.rbmk.ticketguru.ageLimit.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/events", produces = "application/hal+json")
public class EventController {

    @Autowired
    EventRepository eRepository;
    @Autowired
    EventTypeRepository etRepository;
    @Autowired
    EventOrganizerRepository eoGroupRepository;
    @Autowired
    VenueRepository vRepository;
    @Autowired
    AgeLimitRepository alRepository;
    @Autowired
    EventTicketRepository eventTicketRepository;

    @PostMapping(produces = "application/hal+json")
    ResponseEntity<?> add(@Valid @RequestBody Event newEvent) {
        try {
            Event event = eRepository.save(newEvent);
            EventLinks eventLinks = new EventLinks(event);
            event.add(eventLinks.getAll());
            Resource<Event> resource = new Resource<Event>(event);
            return ResponseEntity.ok(resource);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Duplicate entry");
        }
    }

    @PatchMapping(value = "/{id}", produces = "application/hal+json")
    ResponseEntity<Resource<Event>> edit(@Valid @RequestBody Event newEvent, @PathVariable Long id) {
        Event event = eRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        if (newEvent.getName() != "") {
            event.setName(newEvent.getName());
        }
        if (newEvent.getEventType() != null) {
            event.setEventType(newEvent.getEventType());
        }
        if (newEvent.getDateTime() != null) {
            event.setDateTime(newEvent.getDateTime());
        }
        if (newEvent.getEventOrganizer() != null) {
            event.setEventOrganizer(newEvent.getEventOrganizer());
        }
        if (newEvent.getVenue() != null) {
            event.setVenue(newEvent.getVenue());
        }
        if (newEvent.getTicketCapacity() != null) {
            event.setTicketCapacity(newEvent.getTicketCapacity());
        }
        if (newEvent.getAgeLimit() != null) {
            event.setAgeLimit(newEvent.getAgeLimit());
        }
        eRepository.save(event);
        EventLinks eventLinks = new EventLinks(event);
        event.add(eventLinks.getAll());
        Resource<Event> resource = new Resource<Event>(event);
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping(value = "/{id}", produces = "application/hal+json")
    ResponseEntity<?> delete(@PathVariable Long id) {
        return eRepository.findById(id).map(m -> {
            eRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
    }

    @GetMapping(produces = "application/hal+json")
    ResponseEntity<Resources<Event>> all() {
        List<Event> events = eRepository.findAll();
        Link link = linkTo(EventController.class).withSelfRel();
        if (events.size() != 0) {
            for (Event event : events) {
                EventLinks eventLinks = new EventLinks(event);
                event.add(eventLinks.getAll());
            }
            Resources<Event> resources = new Resources<Event>(events, link);
            return ResponseEntity.ok(resources);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/{id}", produces = "application/hal+json")
    public ResponseEntity<Resource<Event>> one(@PathVariable Long id) {
        Event event = eRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        EventLinks eventLinks = new EventLinks(event);
        event.add(eventLinks.getAll());
        Resource<Event> resource = new Resource<Event>(event);
        return ResponseEntity.ok(resource);
    }

    @GetMapping(value = "/{id}/eventType", produces = "application/hal+json")
    ResponseEntity<Resource<EventType>> getEventType(@PathVariable Long id) {
        Event event = eRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        EventType eventType = event.getEventType();
        Link selfLink = linkTo(methodOn(EventTypeController.class).one(eventType.getEventType_ID())).withSelfRel();
        Link eventsLink = linkTo(methodOn(EventTypeController.class).getEvents(id)).withRel("events");
        eventType.add(selfLink);
        eventType.add(eventsLink);
        Resource<EventType> resource = new Resource<EventType>(eventType);
        return ResponseEntity.ok(resource);
    }

    @GetMapping(value = "/{id}/eventOrganizer", produces = "application/hal+json")
    ResponseEntity<Resource<EventOrganizer>> getEventOrganizer(@PathVariable Long id) {
        Event event = eRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        EventOrganizer eventOrganizer = event.getEventOrganizer();
        Link selfLink = linkTo(methodOn(EventOrganizerController.class).one(eventOrganizer.getEventOrganizer_ID()))
                .withSelfRel();
        Link eventsLink = linkTo(methodOn(EventOrganizerController.class).getEvents(id)).withRel("events");
        eventOrganizer.add(selfLink);
        eventOrganizer.add(eventsLink);
        Resource<EventOrganizer> resource = new Resource<EventOrganizer>(eventOrganizer);
        return ResponseEntity.ok(resource);
    }

    @GetMapping(value = "/{id}/venue", produces = "application/hal+json")
    ResponseEntity<Resource<Venue>> getVenue(@PathVariable Long id) {
        Event event = eRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        Venue venue = event.getVenue();
        Link selfLink = linkTo(methodOn(VenueController.class).one(venue.getVenue_ID())).withSelfRel();
        Link eventsLink = linkTo(methodOn(VenueController.class).getVenueEvents(id)).withRel("users");
        venue.add(selfLink);
        venue.add(eventsLink);
        Resource<Venue> resource = new Resource<Venue>(venue);
        return ResponseEntity.ok(resource);
    }

    @GetMapping(value = "/{id}/ageLimit", produces = "application/hal+json")
    ResponseEntity<Resource<AgeLimit>> getAgeLimit(@PathVariable Long id) {
        Event event = eRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        AgeLimit ageLimit = event.getAgeLimit();
        Link selfLink = linkTo(methodOn(AgeLimitController.class).one(ageLimit.getAgeLimit_ID())).withSelfRel();
        Link eventsLink = linkTo(methodOn(AgeLimitController.class).getEvents(id)).withRel("users");
        ageLimit.add(selfLink);
        ageLimit.add(eventsLink);
        Resource<AgeLimit> resource = new Resource<AgeLimit>(ageLimit);
        return ResponseEntity.ok(resource);
    }

    @GetMapping(value = "/{id}/eventTickets", produces = "application/hal+json")
    public ResponseEntity<Resources<EventTicket>> getEventTickets(@PathVariable Long id) {
        Event event = eRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        Link link = linkTo(EventController.class).withSelfRel();
        List<EventTicket> eventTickets = event.getEventTickets();
        if (eventTickets.size() != 0) {
            for (EventTicket eventTicket : eventTickets) {
                Long eventTicket_ID = eventTicket.getEventTicket_ID();
                Link selfLink = linkTo(EventTicketController.class).slash(eventTicket_ID).withSelfRel();
                eventTicket.add(selfLink);
            }
            Resources<EventTicket> resources = new Resources<EventTicket>(eventTickets, link);
            return ResponseEntity.ok(resources);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}