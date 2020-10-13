package it.vizzarro.torganizer.service;


import it.vizzarro.torganizer.models.ModeTournament;
import it.vizzarro.torganizer.repository.ModeTournamentFilter;
import it.vizzarro.torganizer.service.model.ModeTournamentSO;

public interface ModeTournamentService extends CrudService<ModeTournamentSO, ModeTournamentFilter, ModeTournament,Long> {

}
