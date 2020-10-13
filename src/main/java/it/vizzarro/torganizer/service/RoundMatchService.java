package it.vizzarro.torganizer.service;


import it.vizzarro.torganizer.models.RoundMatch;
import it.vizzarro.torganizer.repository.GameFormulaFilter;
import it.vizzarro.torganizer.repository.RoundMatchFilter;
import it.vizzarro.torganizer.service.model.RoundMatchSO;

public interface RoundMatchService extends CrudService<RoundMatchSO, RoundMatchFilter, RoundMatch,Long> {

	public RoundMatchSO createNewRound(Long tournamentId) throws ServiceException;

}
