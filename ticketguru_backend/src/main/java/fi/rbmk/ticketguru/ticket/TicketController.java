package fi.rbmk.ticketguru.ticket;

import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import fi.rbmk.ticketguru.eventTicket.EventTicket;
import fi.rbmk.ticketguru.eventTicket.EventTicketLinks;
import fi.rbmk.ticketguru.eventTicket.EventTicketRepository;
import fi.rbmk.ticketguru.saleRow.SaleRow;
import fi.rbmk.ticketguru.ticketStatus.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/tickets", produces = "application/hal+json")
public class TicketController {

    @Autowired
    TicketRepository tRepository;
    TicketStatusRepository tSRepository;
    EventTicketRepository etRepository;

    @PatchMapping(value = "/{id}", produces = "application/hal+json")
    ResponseEntity<Ticket> edit(@Valid @RequestBody Ticket newTicket, @PathVariable Long id) {
        Ticket ticket = tRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        if (newTicket.getTicketStatus() != null) {
            ticket.setTicketStatus(newTicket.getTicketStatus());
        }
        tRepository.save(ticket);
        return ResponseEntity.ok(ticket);
    }

    @DeleteMapping(value = "/{id}", produces = "application/hal+json")
    ResponseEntity<?> delete(@PathVariable Long id) {
        Ticket ticket = tRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        ticket.setInvalid();
        tRepository.save(ticket);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(produces = "application/hal+json")
    ResponseEntity<Resources<Ticket>> all() {
        List<Ticket> tickets = tRepository.findAll();
        Link link = linkTo(TicketController.class).withSelfRel();
        if (tickets.size() != 0) {
            for (Ticket ticket : tickets) {
                TicketLinks ticketLinks = new TicketLinks(ticket);
                ticket.add(ticketLinks.getAll());
            }
            Resources<Ticket> resources = new Resources<Ticket>(tickets, link);
            return ResponseEntity.ok(resources);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/{s}", produces = "application/hal+json")
    public ResponseEntity<Resource<Ticket>> one(@PathVariable String s) {
        Ticket ticket = null;
        try {
            Long id = Long.parseLong(s);
            ticket = tRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        } catch (NumberFormatException e) {
            ticket = tRepository.findByCheckSum(s);
            if (ticket == null) {
                throw new ResourceNotFoundException("Invalid Checksum: " + s);
            }
        }
        TicketLinks ticketLinks = new TicketLinks(ticket);
        ticket.add(ticketLinks.getAll());
        Resource<Ticket> resource = new Resource<Ticket>(ticket);
        return ResponseEntity.ok(resource);
    }

    @GetMapping(value = "/{id}/ticketStatus", produces = "application/hal+json")
    ResponseEntity<Resource<TicketStatus>> getTicketStatus(@PathVariable Long id) {
        Ticket ticket = tRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        TicketStatus ticketStatus = ticket.getTicketStatus();
        TicketStatusLinks ticketStatusLinks = new TicketStatusLinks(ticketStatus);
        ticketStatus.add(ticketStatusLinks.getAll());
        Resource<TicketStatus> resource = new Resource<TicketStatus>(ticketStatus);
        return ResponseEntity.ok(resource);
    }

    @GetMapping(value = "/{id}/eventTicket", produces = "application/hal+json")
    ResponseEntity<Resource<EventTicket>> getEventTicket(@PathVariable Long id) {
        Ticket ticket = tRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        EventTicket eventTicket = ticket.getEventTicket();
        EventTicketLinks eventTicketLinks = new EventTicketLinks(eventTicket);
        eventTicket.add(eventTicketLinks.getAll());
        Resource<EventTicket> resource = new Resource<EventTicket>(eventTicket);
        return ResponseEntity.ok(resource);
    }

    // @GetMapping(value = "/{id}/saleRow", produces = "application/hal+json")
    // ResponseEntity<Resource<SaleRow>> getSaleRow(@PathVariable Long id) {
    // Ticket ticket = tRepository.findById(id).orElseThrow(() -> new
    // ResourceNotFoundException("Invalid ID: " + id));
    // SaleRow saleRow = ticket.getSaleRow();
    // SaleRowLinks saleRowLinks = new saleRowLinks(saleRow);
    // saleRow.add(saleRowLinks.getAll());
    // Resource<SaleRow> resource = new Resource<SaleRow>(saleRow);
    // return ResponseEntity.ok(resource);
    // }
}