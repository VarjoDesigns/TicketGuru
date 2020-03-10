package fi.rbmk.ticketguru.ticketType;

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

import fi.rbmk.ticketguru.eventTicket.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/ticketTypes", produces = "application/hal+json")
public class TicketTypeController {

    @Autowired
    TicketTypeRepository ticketTypeRepository;

    @Autowired
    EventTicketRepository eventTicketRepository;

    @PostMapping(produces = "application/hal+json")
    ResponseEntity<?> add(@Valid @RequestBody TicketType ticketType) {
        try {
            Long id = ticketTypeRepository.save(ticketType).getTicketType_ID();
            Link selfLink = linkTo(TicketTypeController.class).slash(id).withSelfRel();
            Link eventTicketsLink = linkTo(methodOn(TicketTypeController.class).getEventTickets(id))
                    .withRel("eventTickets");
            ticketType.add(selfLink);
            ticketType.add(eventTicketsLink);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Duplicate entry");
        }
        Resource<TicketType> resource = new Resource<TicketType>(ticketType);
        return ResponseEntity.ok(resource);
    }

    @PatchMapping(value = "/{id}", produces = "application/hal+json")
    ResponseEntity<TicketType> edit(@Valid @RequestBody TicketType newTicketType, @PathVariable Long id) {
        TicketType ticketType = ticketTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        if (newTicketType.getName() != "") {
            ticketType.setName(newTicketType.getName());
        }
        ticketTypeRepository.save(ticketType);
        return ResponseEntity.created(URI.create("/" + ticketType.getTicketType_ID())).build();
    }

    @DeleteMapping(value = "/{id}", produces = "application/hal+json")
    ResponseEntity<?> delete(@PathVariable Long id) {
        return ticketTypeRepository.findById(id).map(m -> {
            ticketTypeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
    }

    @GetMapping(produces = "application/hal+json")
    ResponseEntity<Resources<TicketType>> all() {
        List<TicketType> ticketTypes = ticketTypeRepository.findAll();
        Link link = linkTo(TicketTypeController.class).withSelfRel();
        if (ticketTypes.size() != 0) {
            for (TicketType ticketType : ticketTypes) {
                Long id = ticketType.getTicketType_ID();
                Link selfLink = linkTo(TicketTypeController.class).slash(id).withSelfRel();
                Link eventTicketsLink = linkTo(methodOn(TicketTypeController.class).getEventTickets(id))
                        .withRel("eventTickets");
                ticketType.add(selfLink);
                ticketType.add(eventTicketsLink);
            }
            Resources<TicketType> resources = new Resources<TicketType>(ticketTypes, link);
            return ResponseEntity.ok(resources);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/{id}", produces = "application/hal+json")
    public ResponseEntity<Resource<TicketType>> one(@PathVariable Long id) {
        TicketType ticketType = ticketTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        Link selfLink = linkTo(TicketTypeController.class).slash(id).withSelfRel();
        Link eventTicketLink = linkTo(methodOn(TicketTypeController.class).getEventTickets(id)).withRel("eventTicket");
        ticketType.add(selfLink);
        ticketType.add(eventTicketLink);
        Resource<TicketType> resource = new Resource<TicketType>(ticketType);
        return ResponseEntity.ok(resource);
    }

    @GetMapping(value = "/{id}/eventtickets", produces = "application/hal+json")
    public ResponseEntity<Resources<EventTicket>> getEventTickets(@PathVariable Long id) {
        TicketType ticketType = ticketTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        Link link = linkTo(TicketTypeController.class).withSelfRel();
        List<EventTicket> eventTickets = ticketType.getEventTickets();
        if (eventTickets.size() != 0) {
            for (EventTicket eventTicket : eventTickets) {
                Long eventTicket_ID = eventTicket.getEventTicket_ID();
                Link selfLink = linkTo(EventTicketController.class).slash(eventTicket_ID).withSelfRel();
                eventTicket.add(selfLink);
            }
            Resources<EventTicket> resources = new Resources<EventTicket>(eventTickets, link);
            return ResponseEntity.ok(resources);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}