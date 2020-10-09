package it.vizzarro.torganizer.service;

import java.util.List;

import it.vizzarro.torganizer.models.Tournament;
import it.vizzarro.torganizer.repository.TournamentFilter;

public interface TournamentService {

	
	List<Tournament> findAll(TournamentFilter filter);
	Tournament findById(Long id) throws  ServiceException;
	Tournament save(Tournament tournament );
}
