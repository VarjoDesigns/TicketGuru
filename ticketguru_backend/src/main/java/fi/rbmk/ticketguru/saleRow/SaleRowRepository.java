package fi.rbmk.ticketguru.saleRow;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface SaleRowRepository extends CrudRepository<SaleRow, Long> {

	List<SaleRow> findAll();
}
