/**
 * 
 */
package it.vizzarro.torganizer.repository;

import it.vizzarro.torganizer.models.Partecipant;
import it.vizzarro.torganizer.models.Tournament;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Class Repository DAO Tournament for CRUD Tournament.
 * @author Alessandro Vizzarro
 *
 */
public interface PartecipantRepo extends CrudRepository<Partecipant, Long>, JpaSpecificationExecutor<Partecipant>  {

	Iterable<Partecipant> findAll();
	
	

}
