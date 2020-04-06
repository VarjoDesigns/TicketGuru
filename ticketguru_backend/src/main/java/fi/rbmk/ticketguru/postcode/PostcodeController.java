package fi.rbmk.ticketguru.postcode;

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

import fi.rbmk.ticketguru.eventOrganizer.*;
import fi.rbmk.ticketguru.venue.*;

@RestController
@RequestMapping(value = "/api/postcodes", produces = "application/hal+json")
public class PostcodeController {

    @Autowired PostcodeRepository pRepository;
    @Autowired EventOrganizerRepository eoRepository;
    @Autowired VenueRepository vRepository;

    @PostMapping(produces = "application/hal+json")
    ResponseEntity<?> add(@Valid @RequestBody Postcode newPostcode) {
        try {
            Postcode postcode = pRepository.save(newPostcode);
            PostcodeLinks links = new PostcodeLinks(postcode);
            postcode.add(links.getAll());
            Resource<Postcode> resource = new Resource<Postcode>(postcode);
            return ResponseEntity.ok(resource);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Duplicate entry");
        }
    }

    @PatchMapping(value = "/{id}", produces = "application/hal+json")
    ResponseEntity<Postcode> edit(@Valid @RequestBody Postcode newPostcode, @PathVariable Long id) {
        Postcode postcode = pRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        if(newPostcode.getPostcode() != "") { postcode.setPostcode(newPostcode.getPostcode()); }
        if(newPostcode.getCity() != "") { postcode.setCity(newPostcode.getCity()); }
        if(newPostcode.getCountry() != "") { postcode.setCountry(newPostcode.getCountry()); }
        pRepository.save(postcode);
        return ResponseEntity.created(URI.create("/" + postcode.getPostcode_ID())).build();
    }

    @DeleteMapping(value = "/{id}", produces = "application/hal+json")
    ResponseEntity<?> delete(@PathVariable Long id) {
        Postcode postcode = pRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        postcode.setInvalid();
        return ResponseEntity.noContent().build();
    }

    @GetMapping(produces = "application/hal+json")
    ResponseEntity<Resources<Postcode>> all() {
        List<Postcode> postcodes = pRepository.findAll();
        Link link = linkTo(PostcodeController.class).withSelfRel();
        if (postcodes.size() != 0) {
            for (Postcode postcode : postcodes) {
                PostcodeLinks links = new PostcodeLinks(postcode);
                postcode.add(links.getAll());
            }
            Resources<Postcode> resources = new Resources<Postcode>(postcodes, link);
            return ResponseEntity.ok(resources);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/{id}", produces = "application/hal+json")
    public ResponseEntity<Resource<Postcode>> one(@PathVariable Long id) {
        Postcode postcode = pRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        PostcodeLinks links = new PostcodeLinks(postcode);
        postcode.add(links.getAll());
        Resource<Postcode> resource = new Resource<Postcode>(postcode);
        return ResponseEntity.ok(resource);
    }

    @GetMapping(value = "/{id}/eventOrganizers", produces = "application/hal+json")
    public ResponseEntity<Resources<EventOrganizer>> getEventOrganizers(@PathVariable Long id) {
        Postcode postcode = pRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        Link link = linkTo(PostcodeController.class).withSelfRel();
        List<EventOrganizer> eventOrganizers = postcode.getEventOrganizers();
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

    @GetMapping(value = "/{id}/venues", produces = "application/hal+json")
    public ResponseEntity<Resources<Venue>> getVenues(@PathVariable Long id) {
        Postcode postcode = pRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        Link link = linkTo(PostcodeController.class).withSelfRel();
        List<Venue> venues = postcode.getVenues();
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
}
