package fi.rbmk.ticketguru.venue;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Link;

import java.util.Arrays;
import java.util.List;

public class VenueLinks {

    private Link selfLink, postcodeLink, eventsLink;
    private List<Link> linkList;

    public VenueLinks(Venue venue) {
        Long id = venue.getVenue_ID();
        this.selfLink = linkTo(VenueController.class).slash(id).withSelfRel();
        this.postcodeLink = linkTo(methodOn(VenueController.class).getPostcode(id)).withRel("postcode");
        this.eventsLink = linkTo(methodOn(VenueController.class).getEvents(id)).withRel("events");
        this.linkList = Arrays.asList(
            selfLink,
            postcodeLink,
            eventsLink
        );
    }

    // Getters
    public Link getSelfLink() { return selfLink; }
    public Link getPostcodeLink() { return postcodeLink; }
    public Link getEventsLink() { return eventsLink; }
    public List<Link> getAll() { return linkList; }
}