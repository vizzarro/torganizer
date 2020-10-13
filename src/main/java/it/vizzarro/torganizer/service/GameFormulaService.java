package it.vizzarro.torganizer.service;


import it.vizzarro.torganizer.models.GameFormula;
import it.vizzarro.torganizer.models.Tournament;
import it.vizzarro.torganizer.repository.GameFormulaFilter;
import it.vizzarro.torganizer.repository.TournamentFilter;
import it.vizzarro.torganizer.service.model.GameFormulaSO;
import it.vizzarro.torganizer.service.model.TournamentSO;

public interface GameFormulaService extends CrudService<GameFormulaSO, GameFormulaFilter, GameFormula,Long> {

	

}
