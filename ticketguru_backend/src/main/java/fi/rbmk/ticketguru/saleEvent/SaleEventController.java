package fi.rbmk.ticketguru.saleEvent;

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

import fi.rbmk.ticketguru.saleRow.*;
import fi.rbmk.ticketguru.user.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/saleEvents", produces = "application/hal+json")
public class SaleEventController {

	@Autowired
	SaleEventRepository sERepository;
	@Autowired
	UserRepository uRepository;

	@Autowired
	SaleRowRepository sRRepository;

	@PostMapping(produces = "application/hal+json")
	ResponseEntity<?> add(@Valid @RequestBody SaleEvent saleEvent) {
		try {
			Long id = sERepository.save(saleEvent).getSaleEvent_ID();
			Link selfLink = linkTo(SaleEventController.class).slash(id).withSelfRel();
			Link userLink = linkTo(methodOn(SaleEventController.class).getUser(id)).withRel("user");
			saleEvent.add(selfLink);
			saleEvent.add(userLink);
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.badRequest().body("Duplicate entry");
		}
		Resource<SaleEvent> resource = new Resource<SaleEvent>(saleEvent);
		return ResponseEntity.ok(resource);
	}

	@PatchMapping(value = "/{id}", produces = "application/hal+json")
	ResponseEntity<SaleEvent> edit(@Valid @RequestBody SaleEvent newSaleEvent, @PathVariable Long id) {
		SaleEvent saleEvent = sERepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
		if (newSaleEvent.getDateTime() != null) {
			saleEvent.setDateTime(newSaleEvent.getDateTime());
		}
		if (newSaleEvent.getUser() != null) {
			saleEvent.setUser(newSaleEvent.getUser());
		}
		sERepository.save(saleEvent);
		return ResponseEntity.created(URI.create("/api/saleEvents/" + saleEvent.getSaleEvent_ID())).build();
	}

	@DeleteMapping(value = "/{id}", produces = "application/hal+json")
	ResponseEntity<?> delete(@PathVariable Long id) {
		return sERepository.findById(id).map(m -> {
			sERepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
	}

	@GetMapping(produces = "application/hal+json")
	ResponseEntity<Resources<SaleEvent>> all() {
		List<SaleEvent> users = sERepository.findAll();
		Link link = linkTo(SaleEventController.class).withSelfRel();
		if (users.size() != 0) {
			for (SaleEvent saleEvent : users) {
				Long id = saleEvent.getSaleEvent_ID();
				Link selfLink = linkTo(SaleEventController.class).slash(id).withSelfRel();
				Link userLink = linkTo(methodOn(SaleEventController.class).getUser(id)).withRel("user");
				saleEvent.add(selfLink);
				saleEvent.add(userLink);
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
		Link selfLink = linkTo(SaleEventController.class).slash(id).withSelfRel();
		Link userLink = linkTo(methodOn(SaleEventController.class).getUser(id)).withRel("user");
		saleEvent.add(selfLink);
		saleEvent.add(userLink);
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
	public ResponseEntity<Resources<SaleRow>> getSaleEvents(@PathVariable Long id) {
		SaleEvent saleEvent = sERepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
		Link link = linkTo(SaleEventController.class).withSelfRel();
		List<SaleRow> saleRows = saleEvent.getSaleRows();
		if (saleRows.size() != 0) {
			for (SaleRow saleRow : saleRows) {
				Long saleRow_ID = saleRow.getSaleRow_ID();
				Link selfLink = linkTo(SaleRowController.class).slash(saleRow_ID).withSelfRel();
				saleRow.add(selfLink);
			}
			Resources<SaleRow> resources = new Resources<SaleRow>(saleRows, link);
			return ResponseEntity.ok(resources);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

}