package fi.rbmk.ticketguru.ticketStatus;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Link;

import java.util.Arrays;
import java.util.List;

public class TicketStatusLinks {
	
	private Link selfLink, ticketsLink;
	private List<Link> linkList;
	
	public TicketStatusLinks(TicketStatus ticketStatus) {
		Long id = ticketStatus.getTicketStatus_id();
		this.selfLink = linkTo(TicketStatusController.class).slash(id).withSelfRel();
		this.ticketsLink = linkTo(methodOn(TicketStatusController.class).getTickets(id)).withRel("tickets");
		this.linkList = Arrays.asList(selfLink, ticketsLink);
	}
	
	//Getters
	public Link getSelfLink() { return selfLink; }
	public Link getTicketsLink() { return ticketsLink; }
	public List<Link> getAll() { return linkList; }

}
