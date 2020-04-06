package fi.rbmk.ticketguru.saleRow;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fi.rbmk.ticketguru.ticket.*;
import fi.rbmk.ticketguru.ticketStatus.TicketStatusRepository;
import fi.rbmk.ticketguru.eventTicket.*;
import fi.rbmk.ticketguru.saleEvent.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/saleRows", produces = "application/hal+json")
public class SaleRowController {

    @Autowired
    SaleRowRepository sRRepository;
    @Autowired
    SaleEventRepository sERepository;
    @Autowired
    EventTicketRepository etRepository;
    @Autowired
    TicketStatusRepository tsRepository;
    @Autowired
    TicketService tService;


    @PostMapping(produces = "application/hal+json")
    ResponseEntity<?> add(@Valid @RequestBody SaleRow newSaleRow, @RequestParam Long eT, @RequestParam Long count) {
        try {
            SaleRow saleRow = sRRepository.save(newSaleRow);
            EventTicket eventTicket = etRepository.findById(eT)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + eT));
            tService.generateTickets(saleRow, eventTicket, count);
            SaleRowLinks links = new SaleRowLinks(saleRow);
            saleRow.add(links.getAll());
            Resource<SaleRow> resource = new Resource<SaleRow>(saleRow);
            return ResponseEntity.ok(resource);
        } catch (DataIntegrityViolationException e) { return
            ResponseEntity.badRequest().body("Duplicate entry");
        }
    }

    @PatchMapping(value = "/{id}", produces = "application/hal+json")
    ResponseEntity<SaleRow> edit(@Valid @RequestBody SaleRow newSaleRow, @PathVariable Long id) {
        SaleRow SaleRow = sRRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        if (newSaleRow.getSaleEvent() != null) {
            SaleRow.setSaleEvent(newSaleRow.getSaleEvent());
        }
        if (newSaleRow.getDiscount() != null) {
            SaleRow.setDiscount(newSaleRow.getDiscount());
        }
        sRRepository.save(SaleRow);
        return ResponseEntity.created(URI.create("/api/saleRows/" + SaleRow.getSaleRow_ID())).build();
    }

    @DeleteMapping(value = "/{id}", produces = "application/hal+json")
    ResponseEntity<?> delete(@PathVariable Long id) {
    	SaleRow saleRow = sRRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
    	saleRow.setInvalid();
    	sRRepository.save(saleRow);
    	return ResponseEntity.noContent().build();
        }

    @GetMapping(produces = "application/hal+json")
    ResponseEntity<Resources<SaleRow>> all() {
        List<SaleRow> saleRows = sRRepository.findAll();
        Link link = linkTo(SaleRowController.class).withSelfRel();
        if (saleRows.size() != 0) {
            for (SaleRow saleRow : saleRows) {
                SaleRowLinks links = new SaleRowLinks(saleRow);
                saleRow.add(links.getAll());
            }
            Resources<SaleRow> resources = new Resources<SaleRow>(saleRows, link);
            return ResponseEntity.ok(resources);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/{id}", produces = "application/hal+json")
    public ResponseEntity<Resource<SaleRow>> one(@PathVariable Long id) {
        SaleRow saleRow = sRRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        SaleRowLinks links = new SaleRowLinks(saleRow);
        saleRow.add(links.getAll());
        Resource<SaleRow> resource = new Resource<SaleRow>(saleRow);
        return ResponseEntity.ok(resource);
    }

    @GetMapping(value = "/{id}/saleEvent", produces = "application/hal+json")
    ResponseEntity<Resource<SaleEvent>> getSaleEvent(@PathVariable Long id) {
        SaleRow saleRow = sRRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        SaleEvent saleEvent = saleRow.getSaleEvent();
        SaleEventLinks links = new SaleEventLinks(saleEvent);
        saleEvent.add(links.getAll());
        Resource<SaleEvent> resource = new Resource<SaleEvent>(saleEvent);
        return ResponseEntity.ok(resource);
    }

    @GetMapping(value = "/{id}/tickets", produces = "application/hal+json")
    public ResponseEntity<Resources<Ticket>> getTickets(@PathVariable Long id) {
        SaleRow saleRow = sRRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        Link link = linkTo(SaleRowController.class).withSelfRel();
        List<Ticket> tickets = saleRow.getTickets();
        if (tickets.size() != 0) {
            for (Ticket ticket : tickets) {
                TicketLinks links = new TicketLinks(ticket);
                ticket.add(links.getAll());
            }
            Resources<Ticket> resources = new Resources<Ticket>(tickets, link);
            return ResponseEntity.ok(resources);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}