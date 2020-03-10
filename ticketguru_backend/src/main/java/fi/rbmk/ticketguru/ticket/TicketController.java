package fi.rbmk.ticketguru.ticket;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.rbmk.ticketguru.ticketStatus.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/tickets", produces = "application/hal+json")
public class TicketController {

    @Autowired
    TicketRepository tRepository;
    @Autowired
    TicketStatusRepository tSRepository;

    // @PostMapping(produces = "application/hal+json")
    // ResponseEntity<?> add(@Valid @RequestBody Ticket ticket) {
    //     try {
    //         Long id = tRepository.save(ticket).getTicket_ID();
    //         Link selfLink = linkTo(TicketController.class).slash(id).withSelfRel();
    //         Link ticketStatusLink = linkTo(methodOn(TicketController.class).getTicketStatus(id))
    //                 .withRel("ticketStatus");
    //         ticket.add(selfLink);
    //         ticket.add(ticketStatusLink);
    //     } catch (DataIntegrityViolationException e) {
    //         return ResponseEntity.badRequest().body("Duplicate entry");
    //     }
    //     Resource<Ticket> resource = new Resource<Ticket>(ticket);
    //     return ResponseEntity.ok(resource);
    // }

    @PatchMapping(value = "/{id}", produces = "application/hal+json")
    ResponseEntity<Ticket> edit(@Valid @RequestBody Ticket newTicket, @PathVariable Long id) {
        Ticket ticket = tRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        if (newTicket.getEventTicket() != null) {
            ticket.setEventTicket(newTicket.getEventTicket());
        }
        if (newTicket.getTicketStatus() != null) {
            ticket.setTicketStatus(newTicket.getTicketStatus());
        }
        if (newTicket.getCheckSum() != "") {
            ticket.setCheckSum(newTicket.getCheckSum());
        }
        tRepository.save(ticket);
        return ResponseEntity.created(URI.create("/api/tickets/" + ticket.getTicket_ID())).build();
    }

    @DeleteMapping(value = "/{id}", produces = "application/hal+json")
    ResponseEntity<?> delete(@PathVariable Long id) {
        return tRepository.findById(id).map(m -> {
            tRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
    }

    @GetMapping(produces = "application/hal+json")
    ResponseEntity<Resources<Ticket>> all() {
        List<Ticket> tickets = tRepository.findAll();
        Link link = linkTo(TicketController.class).withSelfRel();
        if (tickets.size() != 0) {
            for (Ticket ticket : tickets) {
                Long id = ticket.getTicket_ID();
                Link selfLink = linkTo(TicketController.class).slash(id).withSelfRel();
                Link ticketStatusLink = linkTo(methodOn(TicketController.class).getTicketStatus(id))
                        .withRel("ticketStatus");
                ticket.add(selfLink);
                ticket.add(ticketStatusLink);
            }
            Resources<Ticket> resources = new Resources<Ticket>(tickets, link);
            return ResponseEntity.ok(resources);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/{id}", produces = "application/hal+json")
    public ResponseEntity<Resource<Ticket>> one(@PathVariable Long id) {
        Ticket ticket = tRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        Link selfLink = linkTo(TicketController.class).slash(id).withSelfRel();
        Link ticketStatusLink = linkTo(methodOn(TicketController.class).getTicketStatus(id)).withRel("ticketStatus");
        ticket.add(selfLink);
        ticket.add(ticketStatusLink);
        Resource<Ticket> resource = new Resource<Ticket>(ticket);
        return ResponseEntity.ok(resource);
    }

    @GetMapping(value = "/{id}/ticketStatus", produces = "application/hal+json")
    ResponseEntity<Resource<TicketStatus>> getTicketStatus(@PathVariable Long id) {
        Ticket ticket = tRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        TicketStatus ticketStatus = ticket.getTicketStatus();
        Link selfLink = linkTo(methodOn(TicketStatusController.class).one(ticketStatus.getTicketStatus_ID()))
                .withSelfRel();
        Link ticketsLink = linkTo(methodOn(TicketStatusController.class).getTickets(id)).withRel("tickets");
        ticketStatus.add(selfLink);
        ticketStatus.add(ticketsLink);
        Resource<TicketStatus> resource = new Resource<TicketStatus>(ticketStatus);
        return ResponseEntity.ok(resource);
    }
}