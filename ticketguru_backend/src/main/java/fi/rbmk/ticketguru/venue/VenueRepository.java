package fi.rbmk.ticketguru.venue;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface VenueRepository extends CrudRepository<Venue, Long> {

    List<Venue> findAll();
}