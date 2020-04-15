package fi.rbmk.ticketguru.saleEvent;

import java.net.URI;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

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

import fi.rbmk.ticketguru.constraintViolationParser.ConstraintViolationParser;
import fi.rbmk.ticketguru.saleRow.*;
import fi.rbmk.ticketguru.user.*;

@RestController
@RequestMapping(value = "/api/saleEvents", produces = "application/hal+json")
public class SaleEventController {

	@Autowired
	SaleEventRepository sERepository;
	@Autowired
	UserRepository uRepository;
	@Autowired
	SaleRowRepository sRRepository;

	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@PostMapping(produces = "application/hal+json")
	ResponseEntity<?> add(@Valid @RequestBody SaleEvent newSaleEvent) {
		try {
			if (newSaleEvent.getUser().getInvalid() != null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot link User that is marked as deleted");
			}
			SaleEvent saleEvent = sERepository.save(newSaleEvent);
			SaleEventLinks links = new SaleEventLinks(saleEvent);
			saleEvent.add(links.getAll());
			Resource<SaleEvent> resource = new Resource<SaleEvent>(saleEvent);
			return ResponseEntity.created(URI.create(saleEvent.getId().getHref())).body(resource);
		} catch (DataIntegrityViolationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicate entry");
		}
	}

	@PatchMapping(value = "/{id}", produces = "application/hal+json")
	ResponseEntity<?> edit(@Valid @RequestBody SaleEvent newSaleEvent, @PathVariable Long id) {
		SaleEvent saleEvent = sERepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
		if (saleEvent.getInvalid() != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot modify SaleEvent that is marked as deleted");
		}
        Set<ConstraintViolation<Object>> violations = validator.validate(newSaleEvent);
        if (!violations.isEmpty()) {
            ConstraintViolationParser constraintViolationParser = new ConstraintViolationParser(violations, HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(constraintViolationParser.parse());
        }
		if (newSaleEvent.getUser() != null && newSaleEvent.getUser() != saleEvent.getUser()) {
			saleEvent.setUser(newSaleEvent.getUser());
		}
		sERepository.save(saleEvent);
		SaleEventLinks links = new SaleEventLinks(saleEvent);
		saleEvent.add(links.getAll());
		Resource<SaleEvent> resource = new Resource<SaleEvent>(saleEvent);
		return ResponseEntity.ok(resource);
	}

	@DeleteMapping(value = "/{id}", produces = "application/hal+json")
	ResponseEntity<?> delete(@PathVariable Long id) {
		SaleEvent saleEvent = sERepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
		if (saleEvent.getInvalid() != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot modify SaleEvent that is marked as deleted");
		}
		saleEvent.setInvalid();
		sERepository.save(saleEvent);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(produces = "application/hal+json")
	ResponseEntity<Resources<SaleEvent>> all() {
		List<SaleEvent> users = sERepository.findAll();
		Link link = linkTo(SaleEventController.class).withSelfRel();
		if (users.size() != 0) {
			for (SaleEvent saleEvent : users) {
				SaleEventLinks links = new SaleEventLinks(saleEvent);
				saleEvent.add(links.getAll());
			}
			Resources<SaleEvent> resources = new Resources<SaleEvent>(users, link);
			return ResponseEntity.ok(resources);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping(value = "/{id}", produces = "application/hal+json")
	public ResponseEntity<Resource<SaleEvent>> one(@PathVariable Long id) {
		SaleEvent saleEvent = sERepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
		SaleEventLinks links = new SaleEventLinks(saleEvent);
		saleEvent.add(links.getAll());
		Resource<SaleEvent> resource = new Resource<SaleEvent>(saleEvent);
		return ResponseEntity.ok(resource);
	}

	@GetMapping(value = "/{id}/user", produces = "application/hal+json")
	ResponseEntity<Resource<User>> getUser(@PathVariable Long id) {
		SaleEvent saleEvent = sERepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
		User user = saleEvent.getUser();
		UserLinks userLinks = new UserLinks(user);
		user.add(userLinks.getAll());
		Resource<User> resource = new Resource<User>(user);
		return ResponseEntity.ok(resource);
	}

	@GetMapping(value = "/{id}/saleRows", produces = "application/hal+json")
	public ResponseEntity<Resources<SaleRow>> getSaleRows(@PathVariable Long id) {
		SaleEvent saleEvent = sERepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
		Link link = linkTo(SaleEventController.class).withSelfRel();
		List<SaleRow> saleRows = saleEvent.getSaleRows();
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

}