package fi.rbmk.ticketguru.eventType;

import java.net.URI;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fi.rbmk.ticketguru.constraintViolationParser.ConstraintViolationParser;
import fi.rbmk.ticketguru.event.*;

@RestController
@RequestMapping(value = "/api/eventTypes", produces = "application/hal+json")
public class EventTypeController {

    @Autowired EventTypeRepository etRepository;
    @Autowired EventRepository eRepository;

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @PostMapping(produces = "application/hal+json")
    ResponseEntity<?> add(@Valid @RequestBody EventType newEventType) {
        try {
            EventType eventType = etRepository.save(newEventType);
            EventTypeLinks links = new EventTypeLinks(eventType);
            eventType.add(links.getAll());
            Resource<EventType> resource = new Resource<EventType>(eventType);
            return ResponseEntity.created(URI.create(eventType.getId().getHref())).body(resource);
        } catch (DuplicateKeyException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicate entry");
        }
    }

    @PatchMapping(value = "/{id}", produces = "application/hal+json")
    ResponseEntity<?> edit(@RequestBody EventType newEventType, @PathVariable Long id) {
        EventType eventType = etRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        if (eventType.getInvalid() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot modify EventType that is marked as deleted");
        }
        Set<ConstraintViolation<Object>> violations = validator.validate(newEventType);
        if (!violations.isEmpty()) {
            ConstraintViolationParser constraintViolationParser = new ConstraintViolationParser(violations, HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(constraintViolationParser.parse());
        }
        if(newEventType.getName() != null && newEventType.getName() != eventType.getName()) {
            eventType.setName(newEventType.getName());
        }
        if(newEventType.getInfo() != null && newEventType.getInfo() != eventType.getInfo()) {
            eventType.setInfo(newEventType.getInfo());
        }
        etRepository.save(eventType);
        EventTypeLinks links = new EventTypeLinks(eventType);
        eventType.add(links.getAll());
        Resource<EventType> resource = new Resource<EventType>(eventType);
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping(value = "/{id}", produces = "application/hal+json")
    ResponseEntity<?> delete(@PathVariable Long id) {
        EventType eventType = etRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        if (eventType.getInvalid() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot modify EventType that is marked as deleted");
        }
        eventType.setInvalid();
        etRepository.save(eventType);
        return ResponseEntity.noContent().build();
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