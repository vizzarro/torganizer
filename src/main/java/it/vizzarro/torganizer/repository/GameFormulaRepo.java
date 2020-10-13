package it.vizzarro.torganizer.repository;

import it.vizzarro.torganizer.models.GameFormula;
import it.vizzarro.torganizer.models.ModeTournament;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface GameFormulaRepo extends CrudRepository<GameFormula, Long>, JpaSpecificationExecutor<GameFormula> {
}
