package it.vizzarro.torganizer.service;


import it.vizzarro.torganizer.models.Partecipant;
import it.vizzarro.torganizer.repository.PartecipantFilter;
import it.vizzarro.torganizer.service.model.PartecipantSO;
import it.vizzarro.torganizer.service.model.TournamentSO;

public interface PartecipantService extends CrudService<PartecipantSO, PartecipantFilter, Partecipant,Long> {

	PartecipantSO createPartecipant() throws ServiceException;

}
