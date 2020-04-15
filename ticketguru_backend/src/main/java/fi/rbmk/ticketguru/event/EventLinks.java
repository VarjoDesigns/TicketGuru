package fi.rbmk.ticketguru.event;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Link;

import java.util.Arrays;
import java.util.List;

public class EventLinks {

    private Link selfLink, eventTypeLink, eventOrganizerLink, venueLink, ageLimitLink, eventTicketsLink;
    private List<Link> linkList;

    public EventLinks(Event event) {
        Long id = event.getEvent_id();
        this.selfLink = linkTo(EventController.class).slash(id).withSelfRel();
        this.eventTypeLink = linkTo(methodOn(EventController.class).getEventType(id)).withRel("eventType");
        this.eventOrganizerLink = linkTo(methodOn(EventController.class).getEventOrganizer(id)).withRel("eventOrganizer");
        this.venueLink = linkTo(methodOn(EventController.class).getVenue(id)).withRel("venue");
        this.ageLimitLink = linkTo(methodOn(EventController.class).getAgeLimit(id)).withRel("ageLimit");
        this.eventTicketsLink = linkTo(methodOn(EventController.class).getEventTickets(id)).withRel("eventTickets");
        this.linkList = Arrays.asList(
            selfLink,
            eventTypeLink,
            eventOrganizerLink,
            venueLink,
            ageLimitLink,
            eventTicketsLink
        );
    }

    // Getters
    public Link getSelfLink() { return selfLink; }
    public Link getEventTypeLink() { return eventTypeLink; }
    public Link getEventOrganizerLink() { return eventOrganizerLink; }
    public Link getVenueLink() { return venueLink; }
    public Link getAgeLimitLink() { return ageLimitLink; }
    public Link getEventTicketsLink() { return eventTicketsLink; }
    public List<Link> getAll() { return linkList; }
}