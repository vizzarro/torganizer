/**
 * 
 */
package it.vizzarro.torganizer.controllers;

import it.vizzarro.torganizer.models.Tournament;
import it.vizzarro.torganizer.repository.TournamentFilter;
import it.vizzarro.torganizer.service.ServiceException;
import it.vizzarro.torganizer.service.TournamentService;
import it.vizzarro.torganizer.utils.jsend.JSendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	public ResponseEntity<JSendResponse> find(@Param("code") String code) {
		TournamentFilter filter = new TournamentFilter();
		filter.setCode(code);
		List<Tournament> result = tournamentService.findAll(filter);
		return ResponseEntity.ok(JSendResponse.success("tournaments",result));
	}

	@GetMapping(value = "/{idTournament}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSendResponse> findById(@PathVariable("idTournament") Long id) {

		Tournament result = null;
		try {
			result = tournamentService.findById(id);
			return ResponseEntity.ok(JSendResponse.success("tournament",result));
		} catch (ServiceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSendResponse.error( new Long(HttpStatus.INTERNAL_SERVER_ERROR.value()),e.getMessage()));
		}
	}
	
	@PostMapping
	public ResponseEntity<JSendResponse> save(@RequestBody Tournament tournament) {
		try {
	
			Tournament tournamentUpdated = getTournamentService().save(tournament);		
			return ResponseEntity.ok(JSendResponse.success("tournament",tournamentUpdated));
		}catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
}
