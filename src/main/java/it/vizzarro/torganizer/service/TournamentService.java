package it.vizzarro.torganizer.service;

import java.util.List;

import it.vizzarro.torganizer.models.Tournament;
import it.vizzarro.torganizer.repository.TournamentFilter;

public interface TournamentService {

	
	public List<Tournament> findAll(TournamentFilter filter);
	public Tournament save(Tournament tournament );
}
