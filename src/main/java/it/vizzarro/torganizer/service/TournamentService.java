package it.vizzarro.torganizer.service;


import it.vizzarro.torganizer.models.Tournament;
import it.vizzarro.torganizer.repository.TournamentFilter;
import it.vizzarro.torganizer.service.model.TournamentSO;

public interface TournamentService extends CrudService<TournamentSO, TournamentFilter,Tournament,Long> {

	

}
