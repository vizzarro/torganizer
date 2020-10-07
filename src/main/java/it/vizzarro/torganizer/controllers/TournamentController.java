/**
 * 
 */
package it.vizzarro.torganizer.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.vizzarro.torganizer.models.Tournament;
import it.vizzarro.torganizer.repository.TournamentFilter;
import it.vizzarro.torganizer.service.TournamentService;

/**
 * @author Alessandro Vizzarro
 *
 */
@RestController
@RequestMapping("/api/tournament")
public class TournamentController {

	private static final Logger logger = LoggerFactory.getLogger(TournamentController.class);

	private TournamentService tournamentService;
	
	
	
	public TournamentController(@Autowired TournamentService tournamentService) {
		super();
		this.tournamentService = tournamentService;
	}



	public TournamentService getTournamentService() {
		return tournamentService;
	}



	@GetMapping
	public ResponseEntity<List<Tournament>> find(@Param("code") String code) {
		TournamentFilter filter = new TournamentFilter();
		filter.setCode(code);
		return ResponseEntity.ok(tournamentService.findAll(filter));
	}
	
	@PostMapping
	public ResponseEntity<Tournament> save(@RequestBody Tournament tournament) {
		try {
	
			Tournament tournamentUpdated = getTournamentService().save(tournament);		
			return ResponseEntity.ok(tournamentUpdated);
		}catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	
	
}
