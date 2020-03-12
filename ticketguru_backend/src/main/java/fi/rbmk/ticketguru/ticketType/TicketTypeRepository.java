package fi.rbmk.ticketguru.ticketType;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TicketTypeRepository extends CrudRepository<TicketType, Long> {

	List<TicketType> findByName(String name);
	List<TicketType> findAll();

}