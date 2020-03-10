package fi.rbmk.ticketguru.userGroup;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserGroupRepository extends CrudRepository<UserGroup, Long> {

	List<UserGroup> findAll();

}
