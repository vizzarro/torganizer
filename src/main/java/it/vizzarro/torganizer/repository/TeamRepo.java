package it.vizzarro.torganizer.repository;

import it.vizzarro.torganizer.models.Team;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Class Repository DAO Team for CRUD Teams.
 * @author Alessandro Vizzarro
 *
 */
public interface TeamRepo extends CrudRepository<Team, Long>, JpaSpecificationExecutor<Team> {
}
