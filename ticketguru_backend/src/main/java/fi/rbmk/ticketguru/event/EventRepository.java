package fi.rbmk.ticketguru.event;

import fi.rbmk.ticketguru.venue.Venue;
import fi.rbmk.ticketguru.eventOrganizer.EventOrganizer;
import fi.rbmk.ticketguru.eventType.EventType;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {

    List<Event> findByName(String name);

    List<Event> findByEventType(EventType eventType);

    List<Event> findByEventOrganizer(EventOrganizer eventOrganizer);

    List<Event> findByVenue(Venue venue);

    List<Event> findAll();
}
