/**
 * 
 */
package it.vizzarro.torganizer.repository;

import it.vizzarro.torganizer.models.RoundMatch;
import it.vizzarro.torganizer.models.Tournament;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Class Repository DAO Round Match for CRUD Tournament.
 * @author Alessandro Vizzarro
 *
 */
public interface RoundMatchRepo extends CrudRepository<RoundMatch, Long>, JpaSpecificationExecutor<RoundMatch>  {

	Iterable<RoundMatch> findAll();
	
	

}
