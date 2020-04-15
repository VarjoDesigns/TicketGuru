package fi.rbmk.ticketguru.venue;

import java.net.URI;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import fi.rbmk.ticketguru.postcode.*;
import fi.rbmk.ticketguru.constraintViolationParser.ConstraintViolationParser;
import fi.rbmk.ticketguru.event.*;

@RestController
@RequestMapping(value = "/api/venues", produces = "application/hal+json")
public class VenueController {

    @Autowired
    VenueRepository venueRepository;
    @Autowired
    PostcodeRepository postcodeRepository;

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @PostMapping(produces = "application/hal+json")
    ResponseEntity<?> add(@Valid @RequestBody Venue newVenue) {
        try {
            if (newVenue.getPostcode().getInvalid() != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot link Postcode that is marked as deleted");
            }
            Venue venue = venueRepository.save(newVenue);
            VenueLinks links = new VenueLinks(venue);
            venue.add(links.getAll());
            Resource<Venue> resource = new Resource<Venue>(venue);
            return ResponseEntity.created(URI.create(venue.getId().getHref())).body(resource);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicate entry");
        }
    }

    @PatchMapping(value = "/{id}", produces = "application/hal+json")
    ResponseEntity<?> edit(@Valid @RequestBody Venue newVenue, @PathVariable Long id) {
        Venue venue = venueRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        if (venue.getInvalid() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot modify Venue that is marked as deleted");
        }
        Set<ConstraintViolation<Object>> violations = validator.validate(newVenue);
        if (!violations.isEmpty()) {
            ConstraintViolationParser constraintViolationParser = new ConstraintViolationParser(violations, HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(constraintViolationParser.parse());
        }
        if (newVenue.getName() != null && newVenue.getName() != venue.getName()) {
            venue.setName(newVenue.getName());
        }
        if (newVenue.getAddress() != null && newVenue.getAddress() != venue.getAddress()) {
            venue.setAddress(newVenue.getAddress());
        }
        if (newVenue.getPostcode() != null && newVenue.getPostcode() != venue.getPostcode()) {
            if (newVenue.getPostcode().getInvalid() != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot link Postcode that is marked as deleted");
            }
            venue.setPostcode(newVenue.getPostcode());
        }
        if (newVenue.getTel() != null && newVenue.getTel() != venue.getTel()) {
            venue.setTel(newVenue.getTel());
        }
        if (newVenue.getEmail() != null && newVenue.getEmail() != venue.getEmail()) {
            venue.setEmail(newVenue.getEmail());
        }
        if (newVenue.getWWW() != null && newVenue.getWWW() != venue.getWWW()) {
            venue.setWWW(newVenue.getWWW());
        }
        if (newVenue.getContactPerson() != null && newVenue.getContactPerson() != venue.getContactPerson()) {
            venue.setContactPerson(newVenue.getContactPerson());
        }
        venueRepository.save(venue);
        VenueLinks links = new VenueLinks(venue);
        venue.add(links.getAll());
        Resource<Venue> resource = new Resource<Venue>(venue);
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping(value = "/{id}", produces = "application/hal+json")
    ResponseEntity<?> delete(@PathVariable Long id) {
        Venue venue = venueRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        if (venue.getInvalid() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot modify Venue that is marked as deleted");
        }
    	venue.setInvalid();
    	venueRepository.save(venue);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(produces = "application/hal+json")
    ResponseEntity<Resources<Venue>> all() {
        List<Venue> venues = venueRepository.findAll();
        Link link = linkTo(VenueController.class).withSelfRel();
        if (venues.size() != 0) {
            for (Venue venue : venues) {
                VenueLinks links = new VenueLinks(venue);
                venue.add(links.getAll());
            }
            Resources<Venue> resources = new Resources<Venue>(venues, link);
            return ResponseEntity.ok(resources);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/{id}", produces = "application/hal+json")
    public ResponseEntity<Resource<Venue>> one(@PathVariable Long id) {
        Venue venue = venueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        VenueLinks links = new VenueLinks(venue);
        venue.add(links.getAll());
        Resource<Venue> resource = new Resource<Venue>(venue);
        return ResponseEntity.ok(resource);
    }

    @GetMapping(value = "/{id}/postcode", produces = "application/hal+json")
    ResponseEntity<Resource<Postcode>> getPostcode(@PathVariable Long id) {
        Venue venue = venueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        Postcode postcode = venue.getPostcode();
        PostcodeLinks links = new PostcodeLinks(postcode);
        postcode.add(links.getAll());
        Resource<Postcode> resource = new Resource<Postcode>(postcode);
        return ResponseEntity.ok(resource);
    }

    @GetMapping(value = "/{id}/events", produces = "application/hal+json")
    public ResponseEntity<Resources<Event>> getEvents(@PathVariable Long id) {
        Venue venue = venueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        Link link = linkTo(VenueController.class).withSelfRel();
        List<Event> events = venue.getEvents();
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