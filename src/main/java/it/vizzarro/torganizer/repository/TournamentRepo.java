/**
 * 
 */
package it.vizzarro.torganizer.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import it.vizzarro.torganizer.models.Tournament;

/**
 * Class Repository DAO Tournament for CRUD Tournament.
 * @author Alessandro Vizzarro
 *
 */
public interface TournamentRepo extends CrudRepository<Tournament, Long>, JpaSpecificationExecutor<Tournament>  {

	Iterable<Tournament> findAll();
	
	

}
