package fi.rbmk.ticketguru.ageLimit;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Link;

import java.util.Arrays;
import java.util.List;

public class AgeLimitLinks {

    private Link selfLink, eventsLink;
    private List<Link> linkList;

    public AgeLimitLinks(AgeLimit ageLimit) {
        Long id = ageLimit.getAgeLimit_ID();
        this.selfLink = linkTo(AgeLimitController.class).slash(id).withSelfRel();
        this.eventsLink = linkTo(methodOn(AgeLimitController.class).getEvents(id)).withRel("events");
        this.linkList = Arrays.asList(selfLink, eventsLink);
    }

    // Getters
    public Link getSelfLink() { return selfLink; }
    public Link getEventsLink() { return eventsLink; }
    public List<Link> getAll() { return linkList; }
}