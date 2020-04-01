package fi.rbmk.ticketguru.ticketStatus;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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

import fi.rbmk.ticketguru.ticket.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/ticketstatuses", produces = "application/hal+json")
public class TicketStatusController {

    @Autowired
    TicketStatusRepository tSRepository;
    @Autowired
    TicketRepository tRepository;

    @PostMapping(produces = "application/hal+json")
    public ResponseEntity<?> add(@Valid @RequestBody TicketStatus newTicketStatus) {
        try {
            TicketStatus ticketStatus = tSRepository.save(newTicketStatus);
            TicketStatusLinks links = new TicketStatusLinks(ticketStatus);
            ticketStatus.add(links.getAll());
            Resource<TicketStatus> resource = new Resource<TicketStatus>(ticketStatus);
            return ResponseEntity.created(URI.create("/api/ticketStatuses/" + ticketStatus.getTicketStatus_ID()))
                    .body(resource);
        } catch (DuplicateKeyException e) {
            return ResponseEntity.badRequest().body("Duplicate entry");
        }
    }

    /*
     * Long id = tSRepository.save(ticketStatus).getTicketStatus_ID(); Link selfLink
     * = linkTo(TicketStatusController.class).slash(id).withSelfRel(); Link
     * ticketsLink =
     * linkTo(methodOn(TicketStatusController.class).getTickets(id)).withRel(
     * "tickets"); ticketStatus.add(selfLink); ticketStatus.add(ticketsLink); }
     * catch (DataIntegrityViolationException e) { return
     * ResponseEntity.badRequest().body("Duplicate entry"); } Resource<TicketStatus>
     * resource = new Resource<TicketStatus>(ticketStatus); return
     * ResponseEntity.ok(resource); }
     */

    @PatchMapping(value = "/{id}", produces = "application/hal+json")
    public ResponseEntity<Resource<TicketStatus>> edit(@Valid @RequestBody TicketStatus newTicketStatus,
            @PathVariable Long id) {
        TicketStatus ticketStatus = tSRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID: + id"));
        if (newTicketStatus.getName() != "" || newTicketStatus.getName() != ticketStatus.getName()) {
            ticketStatus.setName(newTicketStatus.getName());
        }
        tSRepository.save(ticketStatus);
        Resource<TicketStatus> resource = new Resource<TicketStatus>(ticketStatus);
        return ResponseEntity.ok(resource);
        // created(URI.create("/" + ticketStatus.getTicketStatus_ID())).build();
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        return tSRepository.findById(id).map(m -> {
            tSRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
    }

    @GetMapping(produces = "application/hal+json")
    ResponseEntity<Resources<TicketStatus>> all() {
        List<TicketStatus> ticketStatuses = tSRepository.findAll();
        Link link = linkTo(TicketStatusController.class).withSelfRel();
        if (ticketStatuses.size() != 0) {
            for (TicketStatus ticketStatus : ticketStatuses) {
                /*
                 * Long id = ticketStatus.getTicketStatus_ID(); Link selfLink =
                 * linkTo(TicketStatusController.class).slash(id).withSelfRel(); Link
                 * ticketsLink =
                 * linkTo(methodOn(TicketStatusController.class).getTickets(id)).withRel(
                 * "tickets"); ticketStatus.add(selfLink); ticketStatus.add(ticketsLink);
                 */
                TicketStatusLinks ticketStatusLinks = new TicketStatusLinks(ticketStatus);
                ticketStatus.add(ticketStatusLinks.getAll());
            }
            Resources<TicketStatus> resources = new Resources<TicketStatus>(ticketStatuses, link);
            return ResponseEntity.ok(resources);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/{id}", produces = "application/hal+json")
    public ResponseEntity<Resource<TicketStatus>> one(@PathVariable Long id) {
        TicketStatus ticketStatus = tSRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        /*
         * Link selfLink = linkTo(TicketStatusController.class).slash(id).withSelfRel();
         * Link ticketsLink =
         * linkTo(methodOn(TicketStatusController.class).getTickets(id)).withRel(
         * "tickets"); ticketStatus.add(selfLink); ticketStatus.add(ticketsLink);
         */
        TicketStatusLinks links = new TicketStatusLinks(ticketStatus);
        ticketStatus.add(links.getAll());
        Resource<TicketStatus> resource = new Resource<TicketStatus>(ticketStatus);
        return ResponseEntity.ok(resource);
    }

    @GetMapping(value = "/{id}/tickets", produces = "application/hal+json")
    public ResponseEntity<Resources<Ticket>> getTickets(@PathVariable Long id) {
        TicketStatus ticketStatus = tSRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        Link link = linkTo(TicketStatusController.class).withSelfRel();
        List<Ticket> tickets = ticketStatus.getTickets();
        if (tickets.size() != 0) {
            for (Ticket ticket : tickets) {
                Long ticket_ID = ticket.getTicket_ID();
                Link selfLink = linkTo(TicketController.class).slash(ticket_ID).withSelfRel();
                ticket.add(selfLink);
            }
            Resources<Ticket> resources = new Resources<Ticket>(tickets, link);
            return ResponseEntity.ok(resources);
        } else {
            return ResponseEntity.noContent().build(); // Pitäisikö olla .notFound?
        }
    }
}