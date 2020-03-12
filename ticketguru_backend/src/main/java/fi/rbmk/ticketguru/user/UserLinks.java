package fi.rbmk.ticketguru.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Link;

import java.util.Arrays;
import java.util.List;

public class UserLinks {

    private Link selfLink, userGroupLink, saleEventLink;
    private List<Link> linkList;

    public UserLinks(User user) {
        Long id = user.getUser_ID();
        this.selfLink = linkTo(UserController.class).slash(id).withSelfRel();
        this.userGroupLink = linkTo(methodOn(UserController.class).getUserGroup(id)).withRel("userGroup");
        this.saleEventLink = linkTo(methodOn(UserController.class).getSaleEvent(id)).withRel("saleEvent");
        this.linkList = Arrays.asList(selfLink, userGroupLink, saleEventLink);
    }

    // Getters
    public Link getSelfLink() {
        return selfLink;
    }

    public Link getUserGroupLink() {
        return userGroupLink;
    }

    public Link getSaleEventLink() {
        return saleEventLink;
    }

    public List<Link> getAll() {
        return linkList;
    }
}