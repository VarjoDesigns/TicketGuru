package fi.rbmk.ticketguru.event;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import org.springframework.data.rest.webmvc.ResourceNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventLinksTest {

    @Autowired 
    private EventRepository eventRepository;

    @Autowired 

    @Test
    public void whenFoundEvent_getEventLinks() {

        String baseUrl = "http://localhost/api";
        Event event = eventRepository.findById(Integer.toUnsignedLong(1)).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: 1"));

        EventLinks links = new EventLinks(event);
        event.add(links.getAll());

        assertEquals(baseUrl + "/events/1/eventType", event.getLink("eventType").getHref());
        assertEquals(baseUrl + "/events/1/eventOrganizer", event.getLink("eventOrganizer").getHref());
        assertEquals(baseUrl + "/events/1/venue", event.getLink("venue").getHref());
        assertEquals(baseUrl + "/events/1/ageLimit", event.getLink("ageLimit").getHref());
        assertEquals(baseUrl + "/events/1/eventTickets", event.getLink("eventTickets").getHref());
    }
}