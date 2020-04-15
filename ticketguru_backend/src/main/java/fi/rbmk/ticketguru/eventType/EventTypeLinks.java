package fi.rbmk.ticketguru.eventType;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Link;

import java.util.Arrays;
import java.util.List;

public class EventTypeLinks {

    private Link selfLink, eventsLink;
    private List<Link> linkList;

    public EventTypeLinks(EventType eventType) {
        Long id = eventType.getEventType_id();
        this.selfLink = linkTo(EventTypeController.class).slash(id).withSelfRel();
        this.eventsLink = linkTo(methodOn(EventTypeController.class).getEvents(id)).withRel("events");
        this.linkList = Arrays.asList(
            selfLink,
            eventsLink
        );
    }

    // Getters
    public Link getSelfLink() { return selfLink; }
    public Link getEventsLink() { return eventsLink; }
    public List<Link> getAll() { return linkList; }
}