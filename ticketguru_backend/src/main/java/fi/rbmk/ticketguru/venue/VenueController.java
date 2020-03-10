package fi.rbmk.ticketguru.venue;

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
@RequestMapping(value = "/api/venues", produces = "application/hal+json")
public class VenueController {

    @Autowired
    VenueRepository venueRepository;
    @Autowired
    PostcodeRepository postcodeRepository;

    @PostMapping(produces = "application/hal+json")
    ResponseEntity<?> add(@Valid @RequestBody Venue venue) {
        try {
            Long id = venueRepository.save(venue).getVenue_ID();
            Link selfLink = linkTo(VenueController.class).slash(id).withSelfRel();
            Link postcodeLink = linkTo(methodOn(VenueController.class).getPostcode(id)).withRel("postcode");
            Link eventsLink = linkTo(methodOn(VenueController.class).getVenueEvents(id)).withRel("events");
            venue.add(selfLink);
            venue.add(postcodeLink);
            venue.add(eventsLink);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Duplicate entry");
        }
        Resource<Venue> resource = new Resource<Venue>(venue);
        return ResponseEntity.ok(resource);
    }

    @PatchMapping(value = "/{id}", produces = "application/hal+json")
    ResponseEntity<Venue> edit(@Valid @RequestBody Venue newVenue, @PathVariable Long id) {
        Venue venue = venueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        if (newVenue.getName() != "") {
            venue.setName(newVenue.getName());
        }
        if (newVenue.getAddress() != null) {
            venue.setAddress(newVenue.getAddress());
        }
        if (newVenue.getPostcode() != venue.getPostcode()) {
            venue.setPostcode(newVenue.getPostcode());
        }
        venueRepository.save(venue);
        return ResponseEntity.created(URI.create("/api/venues/" + venue.getId())).build();
    }

    @DeleteMapping(value = "/{id}", produces = "application/hal+json")
    ResponseEntity<?> delete(@PathVariable Long id) {
        return venueRepository.findById(id).map(m -> {
            venueRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
    }

    @GetMapping(produces = "application/hal+json")
    ResponseEntity<Resources<Venue>> all() {
        List<Venue> venues = venueRepository.findAll();
        Link link = linkTo(VenueController.class).withSelfRel();
        if (venues.size() != 0) {
            for (Venue venue : venues) {
                Long id = venue.getVenue_ID();
                Link selfLink = linkTo(VenueController.class).slash(id).withSelfRel();
                Link postcodeLink = linkTo(methodOn(VenueController.class).getPostcode(id)).withRel("postcode");
                Link eventsLink = linkTo(methodOn(VenueController.class).getVenueEvents(id)).withRel("events");
                venue.add(selfLink);
                venue.add(postcodeLink);
                venue.add(eventsLink);
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
        Link selfLink = linkTo(VenueController.class).slash(id).withSelfRel();
        Link postcodeLink = linkTo(methodOn(VenueController.class).getPostcode(id)).withRel("postcode");
        Link eventsLink = linkTo(methodOn(VenueController.class).getVenueEvents(id)).withRel("events");
        venue.add(selfLink);
        venue.add(postcodeLink);
        venue.add(eventsLink);
        Resource<Venue> resource = new Resource<Venue>(venue);
        return ResponseEntity.ok(resource);
    }

    @GetMapping(value = "/{id}/postcode", produces = "application/hal+json")
    ResponseEntity<Resource<Postcode>> getPostcode(@PathVariable Long id) {
        Venue venue = venueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        Postcode postCode = venue.getPostcode();
        Link selfLink = linkTo(methodOn(PostcodeController.class).one(id)).withSelfRel();
        Link venuesLink = linkTo(methodOn(PostcodeController.class).getVenues(id)).withRel("venues");
        Link eventOrganizersLink = linkTo(methodOn(PostcodeController.class).getEventOrganizers(id))
                .withRel("eventOrganizers");
        postCode.add(selfLink);
        postCode.add(venuesLink);
        postCode.add(eventOrganizersLink);
        Resource<Postcode> resource = new Resource<Postcode>(postCode);
        return ResponseEntity.ok(resource);
    }

    @GetMapping(value = "/{id}/events", produces = "application/hal+json")
    public ResponseEntity<Resources<Event>> getVenueEvents(@PathVariable Long id) {
        Venue venue = venueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        Link link = linkTo(VenueController.class).withSelfRel();
        List<Event> events = venue.getEvents();
        if (events.size() != 0) {
            for (Event event : events) {
                Long event_ID = event.getEvent_ID();
                Link selfLink = linkTo(EventController.class).slash(event_ID).withSelfRel();
                event.add(selfLink);
            }
            Resources<Event> resources = new Resources<Event>(events, link);
            return ResponseEntity.ok(resources);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
