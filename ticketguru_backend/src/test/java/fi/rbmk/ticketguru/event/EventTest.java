package fi.rbmk.ticketguru.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import fi.rbmk.ticketguru.ageLimit.AgeLimit;
import fi.rbmk.ticketguru.ageLimit.AgeLimitRepository;
import fi.rbmk.ticketguru.eventOrganizer.EventOrganizer;
import fi.rbmk.ticketguru.eventOrganizer.EventOrganizerRepository;
import fi.rbmk.ticketguru.eventType.EventType;
import fi.rbmk.ticketguru.eventType.EventTypeRepository;
import fi.rbmk.ticketguru.venue.Venue;
import fi.rbmk.ticketguru.venue.VenueRepository;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;


@RunWith(SpringRunner.class)
@DataJpaTest
public class EventTest {

    @Autowired
    private EventTypeRepository eventTypeRepository;
    
    @Autowired
    private EventOrganizerRepository eventOrganizerRepository;
    
    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private AgeLimitRepository ageLimitRepository;

    @Autowired TestEntityManager entityManager;

    @Test
    public void whenCreated_thenReturnEvent() {

        LocalDateTime dateTime = LocalDateTime.of(2020, 3, 1, 20, 0, 0);
        EventType eventType = eventTypeRepository.findById(Integer.toUnsignedLong(1)).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: 1"));
        EventOrganizer eventOrganizer = eventOrganizerRepository.findById(Integer.toUnsignedLong(1)).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: 1"));
        Venue venue = venueRepository.findById(Integer.toUnsignedLong(1)).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: 1"));
        AgeLimit ageLimit = ageLimitRepository.findById(Integer.toUnsignedLong(2)).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: 2"));
        Event event = new Event("Koodari Kemut 2020", eventType, dateTime, eventOrganizer, venue, Integer.toUnsignedLong(1500), ageLimit, "Mika koodaa ja muut kattelee. Kannattaa tulla kauempaakin");
        entityManager.persist(event);
        entityManager.flush();

        assertEquals(ageLimit, event.getAgeLimit());
        assertEquals(eventType, event.getEventType());
        assertEquals(eventOrganizer, event.getEventOrganizer());
        assertEquals(venue, event.getVenue());
        assertEquals(dateTime, event.getDateTime());
        assertEquals(null, event.getInvalid());
        assertNotEquals(null, event.getCreated());
    }
}