/**
 * 
 */
package it.vizzarro.torganizer.repository;

import it.vizzarro.torganizer.models.Match;
import it.vizzarro.torganizer.models.RoundMatch;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Class Repository DAO Round Match for CRUD Tournament.
 * @author Alessandro Vizzarro
 *
 */
public interface MatchRepo extends CrudRepository<Match, Long>, JpaSpecificationExecutor<Match>  {

	

}
