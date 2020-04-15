package fi.rbmk.ticketguru.postcode;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Link;

import java.util.Arrays;
import java.util.List;

public class PostcodeLinks {

    private Link selfLink, eventOrganizersLink, venuesLink;
    private List<Link> linkList;

    public PostcodeLinks(Postcode postcode) {
        String id = postcode.getPostcode_id();
        this.selfLink = linkTo(PostcodeController.class).slash(id).withSelfRel();
        this.eventOrganizersLink = linkTo(methodOn(PostcodeController.class).getEventOrganizers(id)).withRel("eventOrganizers");
        this.venuesLink = linkTo(methodOn(PostcodeController.class).getVenues(id)).withRel("venues");
        // events
        this.linkList = Arrays.asList(
            selfLink,
            eventOrganizersLink,
            venuesLink
        );
    }

    // Getters
    public Link getSelfLink() { return selfLink; }
    public Link getEventOrganizersLink() { return eventOrganizersLink; }
    public Link getVenuesLink() { return venuesLink; }
    public List<Link> getAll() { return linkList; }
}