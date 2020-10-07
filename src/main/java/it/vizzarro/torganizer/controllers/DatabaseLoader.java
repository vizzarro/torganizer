package it.vizzarro.torganizer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import it.vizzarro.torganizer.repository.TournamentRepo;

@Configuration
public class DatabaseLoader {
	
	private TournamentRepo tournamentRepo;
	
	
	public DatabaseLoader(@Autowired TournamentRepo tournamentRepo) {
		super();
		this.tournamentRepo = tournamentRepo;
	}

	

}
