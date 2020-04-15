package fi.rbmk.ticketguru.eventOrganizer;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Link;

import java.util.Arrays;
import java.util.List;

public class EventOrganizerLinks {

    private Link selfLink, postcodeLink, eventsLink;
    private List<Link> linkList;

    public EventOrganizerLinks(EventOrganizer eventOrganizer) {
        Long id = eventOrganizer.getEventOrganizer_id();
        this.selfLink = linkTo(EventOrganizerController.class).slash(id).withSelfRel();
        this.postcodeLink = linkTo(methodOn(EventOrganizerController.class).getPostcode(id)).withRel("postcode");
        this.eventsLink = linkTo(methodOn(EventOrganizerController.class).getEvents(id)).withRel("events");
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