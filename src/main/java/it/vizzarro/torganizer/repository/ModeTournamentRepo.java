package it.vizzarro.torganizer.repository;

import it.vizzarro.torganizer.models.ModeTournament;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ModeTournamentRepo  extends CrudRepository<ModeTournament, Long>, JpaSpecificationExecutor<ModeTournament> {
}
