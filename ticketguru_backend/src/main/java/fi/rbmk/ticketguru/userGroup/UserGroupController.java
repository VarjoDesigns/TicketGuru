package fi.rbmk.ticketguru.userGroup;

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

import fi.rbmk.ticketguru.user.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/userGroups", produces = "application/hal+json")
public class UserGroupController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserGroupRepository userGroupRepository;

    @PostMapping(produces = "application/hal+json")
    ResponseEntity<?> add(@Valid @RequestBody UserGroup userGroup) {
        try {
            Long id = userGroupRepository.save(userGroup).getUserGroup_ID();
            Link selfLink = linkTo(UserGroupController.class).slash(id).withSelfRel();
            Link usersLink = linkTo(methodOn(UserGroupController.class).getUsers(id)).withRel("users");
            userGroup.add(selfLink);
            userGroup.add(usersLink);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Duplicate entry");
        }
        Resource<UserGroup> resource = new Resource<UserGroup>(userGroup);
        return ResponseEntity.ok(resource);
    }

    @PatchMapping(value = "/{id}", produces = "application/hal+json")
    ResponseEntity<UserGroup> edit(@Valid @RequestBody UserGroup newUserGroup, @PathVariable Long id) {
        UserGroup userGroup = userGroupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        if (newUserGroup.getName() != "") {
            userGroup.setName(newUserGroup.getName());
        }
        userGroupRepository.save(userGroup);
        return ResponseEntity.created(URI.create("/" + userGroup.getUserGroup_ID())).build();
    }

    @DeleteMapping(value = "/{id}", produces = "application/hal+json")
    ResponseEntity<?> delete(@PathVariable Long id) {
        return userGroupRepository.findById(id).map(m -> {
            userGroupRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
    }

    @GetMapping(produces = "application/hal+json")
    ResponseEntity<Resources<UserGroup>> all() {
        List<UserGroup> userGroups = userGroupRepository.findAll();
        Link link = linkTo(UserGroupController.class).withSelfRel();
        if (userGroups.size() != 0) {
            for (UserGroup userGroup : userGroups) {
                Long id = userGroup.getUserGroup_ID();
                Link selfLink = linkTo(UserGroupController.class).slash(id).withSelfRel();
                Link usersLink = linkTo(methodOn(UserGroupController.class).getUsers(id)).withRel("users");
                userGroup.add(selfLink);
                userGroup.add(usersLink);
            }
            Resources<UserGroup> resources = new Resources<UserGroup>(userGroups, link);
            return ResponseEntity.ok(resources);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/{id}", produces = "application/hal+json")
    public ResponseEntity<Resource<UserGroup>> one(@PathVariable Long id) {
        UserGroup userGroup = userGroupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        Link selfLink = linkTo(UserGroupController.class).slash(id).withSelfRel();
        Link usersLink = linkTo(methodOn(UserGroupController.class).getUsers(id)).withRel("users");
        userGroup.add(selfLink);
        userGroup.add(usersLink);
        Resource<UserGroup> resource = new Resource<UserGroup>(userGroup);
        return ResponseEntity.ok(resource);
    }

    @GetMapping(value = "/{id}/users", produces = "application/hal+json")
    public ResponseEntity<Resources<User>> getUsers(@PathVariable Long id) {
        UserGroup userGroup = userGroupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        Link link = linkTo(UserGroupController.class).withSelfRel();
        List<User> users = userGroup.getUsers();
        if (users.size() != 0) {
            for (User user : users) {
                Long user_ID = user.getUser_ID();
                Link selfLink = linkTo(UserController.class).slash(user_ID).withSelfRel();
                user.add(selfLink);
            }
            Resources<User> resources = new Resources<User>(users, link);
            return ResponseEntity.ok(resources);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}