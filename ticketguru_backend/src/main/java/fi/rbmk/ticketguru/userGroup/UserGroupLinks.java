package fi.rbmk.ticketguru.userGroup;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Link;

import java.util.Arrays;
import java.util.List;

public class UserGroupLinks {

    private Link selfLink, usersLink;
    private List<Link> linkList;

    public UserGroupLinks(UserGroup userGroup) {
        Long id = userGroup.getUserGroup_ID();
        this.selfLink = linkTo(UserGroupController.class).slash(id).withSelfRel();
        this.usersLink = linkTo(methodOn(UserGroupController.class).getUsers(id)).withRel("users");
        this.linkList = Arrays.asList(selfLink, usersLink);
    }

    // Getters
    public Link getSelfLink() {
        return selfLink;
    }

    public Link getUsersLink() {
        return usersLink;
    }

    public List<Link> getAll() {
        return linkList;
    }
}