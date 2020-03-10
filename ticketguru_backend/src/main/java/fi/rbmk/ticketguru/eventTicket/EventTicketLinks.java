package fi.rbmk.ticketguru.eventTicket;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Link;

import java.util.Arrays;
import java.util.List;

public class EventTicketLinks {

    private Link selfLink, eventLink, ticketTypeLink;
    private List<Link> linkList;

    public EventTicketLinks(EventTicket eventTicket) {
        Long id = eventTicket.getEventTicket_ID();
        this.selfLink = linkTo(EventTicketController.class).slash(id).withSelfRel();
        this.eventLink = linkTo(methodOn(EventTicketController.class).getEvent(id)).withRel("event");
        this.ticketTypeLink = linkTo(methodOn(EventTicketController.class).getTicketType(id)).withRel("ticketType");
        // tickets
        this.linkList = Arrays.asList(
            selfLink,
            eventLink,
            ticketTypeLink
        );
    }

    // Getters
    public Link getSelfLink() { return selfLink; }
    public Link getEventLink() { return eventLink; }
    public Link getTicketTypeLink() { return ticketTypeLink; }
    public List<Link> getAll() { return linkList; }
}