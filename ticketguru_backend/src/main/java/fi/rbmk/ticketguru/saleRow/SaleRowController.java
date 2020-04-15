package fi.rbmk.ticketguru.saleRow;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.fasterxml.jackson.databind.JsonNode;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fi.rbmk.ticketguru.ticket.*;
import fi.rbmk.ticketguru.ticketStatus.TicketStatusRepository;
import fi.rbmk.ticketguru.constraintViolationParser.ConstraintViolationParser;
import fi.rbmk.ticketguru.eventTicket.*;
import fi.rbmk.ticketguru.saleEvent.*;

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

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private Long getIdFromUri(String str) {
        try {
            URI uri = new URI(str);
            String[] uriParts = uri.getPath().split("/");
            if (uriParts.length < 2) {
                return null;
            }
            return Long.parseLong(uriParts[uriParts.length - 1]);
        } catch (URISyntaxException e) {
            return null;
        }
    }

    @PostMapping(produces = "application/hal+json")
    ResponseEntity<?> add(@RequestBody JsonNode requestBody) {
        try {
            SaleEvent saleEvent = sERepository.findById(getIdFromUri(requestBody.get("saleEvent").textValue()))
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID"));
            if (saleEvent.getInvalid() != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot create SaleRow for SaleEvent that is marked as deleted");
            }
            EventTicket eventTicket = etRepository.findById(getIdFromUri(requestBody.get("eventTicket").textValue()))
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID"));
            if (eventTicket.getInvalid() != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot create SaleRow with EventTicket that is marked as deleted");
            }
            Long discount = requestBody.get("discount").asLong();
            SaleRow saleRow = sRRepository.save(new SaleRow(saleEvent, discount));
            tService.generateTickets(saleRow, eventTicket, requestBody.get("count").asLong());
            SaleRowLinks links = new SaleRowLinks(saleRow);
            saleRow.add(links.getAll());
            Resource<SaleRow> resource = new Resource<SaleRow>(saleRow);
            return ResponseEntity.created(URI.create(saleRow.getId().getHref())).body(resource);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicate entry");
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid ID");
        }
    }

    @PatchMapping(value = "/{id}", produces = "application/hal+json")
    ResponseEntity<?> edit(@RequestBody SaleRow newSaleRow, @PathVariable Long id) {
        SaleRow saleRow = sRRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        if (saleRow.getInvalid() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot modify SaleRow that is marked as deleted");
        }
        Set<ConstraintViolation<Object>> violations = validator.validate(newSaleRow);
        if (!violations.isEmpty()) {
            ConstraintViolationParser constraintViolationParser = new ConstraintViolationParser(violations, HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(constraintViolationParser.parse());
        }
        if (newSaleRow.getSaleEvent() != null && newSaleRow.getSaleEvent() != saleRow.getSaleEvent()) {
            saleRow.setSaleEvent(newSaleRow.getSaleEvent());
        }
        if (newSaleRow.getDiscount() != null && newSaleRow.getDiscount() != saleRow.getDiscount()) {
            saleRow.setDiscount(newSaleRow.getDiscount());
        }
        sRRepository.save(saleRow);
        SaleRowLinks links = new SaleRowLinks(saleRow);
        saleRow.add(links.getAll());
        Resource<SaleRow> resource = new Resource<SaleRow>(saleRow);
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping(value = "/{id}", produces = "application/hal+json")
    ResponseEntity<?> delete(@PathVariable Long id) {
        SaleRow saleRow = sRRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        if (saleRow.getInvalid() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot modify SaleRow that is marked as deleted");
        }
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