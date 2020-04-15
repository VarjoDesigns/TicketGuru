package fi.rbmk.ticketguru.ticket;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Link;

import java.util.Arrays;
import java.util.List;

public class TicketLinks {

    private Link selfLink, eventTicketLink, ticketStatusLink, saleRowLink;
    private List<Link> linkList;

    public TicketLinks(Ticket ticket) {
        Long id = ticket.getTicket_id();
        this.selfLink = linkTo(TicketController.class).slash(id).withSelfRel();
        this.eventTicketLink = linkTo(methodOn(TicketController.class).getEventTicket(id)).withRel("eventTicket");
        this.ticketStatusLink = linkTo(methodOn(TicketController.class).getTicketStatus(id)).withRel("ticketStatus");
        //this.saleRowLink = linkTo(methodOn(TicketController.class).getSaleRow(id)).withRel("saleRow");
        this.linkList = Arrays.asList(
            selfLink,
            eventTicketLink,
            ticketStatusLink
            //saleRowLink
        );
    }

    // Getters
    public Link getSelfLink() { return selfLink; }
    public Link getEventTicketLink() { return eventTicketLink; }
    public Link getTicketStatus() { return ticketStatusLink; }
    public Link getSaleRowLink() { return saleRowLink; }
    public List<Link> getAll() { return linkList; }
}