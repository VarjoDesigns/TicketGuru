package fi.rbmk.ticketguru.userGroup;

import java.net.URI;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
import fi.rbmk.ticketguru.user.*;

@RestController
@RequestMapping(value = "/api/userGroups", produces = "application/hal+json")
public class UserGroupController {

    @Autowired
    UserGroupRepository uGRepository;

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @PostMapping(produces = "application/hal+json")
    public ResponseEntity<?> add(@Valid @RequestBody UserGroup newUserGroup) {
        try {
            UserGroup userGroup = uGRepository.save(newUserGroup);
            UserGroupLinks links = new UserGroupLinks(userGroup);
            userGroup.add(links.getAll());
            Resource<UserGroup> resource = new Resource<UserGroup>(userGroup);
            return ResponseEntity.created(URI.create(userGroup.getId().getHref())).body(resource);
        } catch (DuplicateKeyException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicate entry");
        }

    }

    @PatchMapping(value = "/{id}", produces = "application/hal+json")
    public ResponseEntity<?> edit(@RequestBody UserGroup newUserGroup, @PathVariable Long id) {
        UserGroup userGroup = uGRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        if (userGroup.getInvalid() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot modify UserGroup that is marked as deleted");
        }
        Set<ConstraintViolation<Object>> violations = validator.validate(newUserGroup);
        if (!violations.isEmpty()) {
            ConstraintViolationParser constraintViolationParser = new ConstraintViolationParser(violations, HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(constraintViolationParser.parse());
        }
        if (newUserGroup.getName() != null && newUserGroup.getName() != userGroup.getName()) {
            userGroup.setName(newUserGroup.getName());
        }
        uGRepository.save(userGroup);
        UserGroupLinks links = new UserGroupLinks(userGroup);
        userGroup.add(links.getAll());
        Resource<UserGroup> resource = new Resource<UserGroup>(userGroup);
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping(value = "/{id}", produces = "application/hal+json")
    ResponseEntity<?> delete(@PathVariable Long id) {
        UserGroup userGroup = uGRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        if (userGroup.getInvalid() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot modify UserGroup that is marked as deleted");
        }
    	userGroup.setInvalid();
    	uGRepository.save(userGroup);
    	return ResponseEntity.noContent().build();
    }

    @GetMapping(produces = "application/hal+json")
    ResponseEntity<Resources<UserGroup>> all() {
        List<UserGroup> userGroups = uGRepository.findAll();
        Link link = linkTo(UserGroupController.class).withSelfRel();
        if (userGroups.size() != 0) {
            for (UserGroup userGroup : userGroups) {
                UserGroupLinks links = new UserGroupLinks(userGroup);
                userGroup.add(links.getAll());
            }
            Resources<UserGroup> resources = new Resources<UserGroup>(userGroups, link);
            return ResponseEntity.ok(resources);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/{id}", produces = "application/hal+json")
    public ResponseEntity<Resource<UserGroup>> one(@PathVariable Long id) {
        UserGroup userGroup = uGRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        UserGroupLinks links = new UserGroupLinks(userGroup);
        userGroup.add(links.getAll());
        Resource<UserGroup> resource = new Resource<UserGroup>(userGroup);
        return ResponseEntity.ok(resource);
    }

    @GetMapping(value = "/{id}/users", produces = "application/hal+json")
    public ResponseEntity<Resources<User>> getUsers(@PathVariable Long id) {
        UserGroup userGroup = uGRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        Link link = linkTo(UserGroupController.class).withSelfRel();
        List<User> users = userGroup.getUsers();
        if (users.size() != 0) {
            for (User user : users) {
                UserLinks links = new UserLinks(user);
                user.add(links.getAll());
            }
            Resources<User> resources = new Resources<User>(users, link);
            return ResponseEntity.ok(resources);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}