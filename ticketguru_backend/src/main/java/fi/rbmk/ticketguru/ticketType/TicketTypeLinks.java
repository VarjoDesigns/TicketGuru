package fi.rbmk.ticketguru.ticketType;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Link;

import java.util.Arrays;
import java.util.List;

public class TicketTypeLinks {
	
	private Link selfLink, eventTicketsLink;
	private List<Link> linkList;
	
	public TicketTypeLinks(TicketType ticketType) {
		Long id = ticketType.getTicketType_id();
		this.selfLink = linkTo(TicketTypeController.class).slash(id).withSelfRel();
		this.eventTicketsLink = linkTo(methodOn(TicketTypeController.class).getEventTickets(id)).withRel("eventTickets");
		this.linkList = Arrays.asList(selfLink, eventTicketsLink);
	}
	
	//Getters
	public Link getSelfLink() { return selfLink; }
	public Link getEventTicketsLink() { return eventTicketsLink; }
	public List<Link> getAll() { return linkList; }

}
