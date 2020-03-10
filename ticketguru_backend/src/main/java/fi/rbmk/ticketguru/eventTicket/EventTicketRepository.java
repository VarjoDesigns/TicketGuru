package fi.rbmk.ticketguru.eventTicket;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EventTicketRepository extends CrudRepository<EventTicket, Long> {

  List<EventTicket> findAll();

}