/**
 * 
 */
package it.vizzarro.torganizer.controllers;

import it.vizzarro.torganizer.repository.TournamentFilter;
import it.vizzarro.torganizer.service.MatchService;
import it.vizzarro.torganizer.service.RoundMatchService;
import it.vizzarro.torganizer.service.ServiceException;
import it.vizzarro.torganizer.service.TournamentService;
import it.vizzarro.torganizer.service.io.RoundMatchRequest;
import it.vizzarro.torganizer.service.model.MatchSO;
import it.vizzarro.torganizer.service.model.RoundMatchSO;
import it.vizzarro.torganizer.service.model.TournamentSO;
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
	private RoundMatchService roundMatchService;
	private MatchService matchService;

	public TournamentController(@Autowired TournamentService tournamentService,
								@Autowired RoundMatchService roundMatchService,
								@Autowired MatchService matchService) {
		super();
		this.tournamentService = tournamentService;
		this.roundMatchService = roundMatchService;
		this.matchService = matchService;
	}

	public TournamentService getTournamentService() {

		return tournamentService;
	}

	public RoundMatchService getRoundMatchService() {
		return roundMatchService;
	}

	@GetMapping
	public ResponseEntity<JSendResponse> find(@Param("q") String q) {
		try {
			TournamentFilter filter = new TournamentFilter(q);
			//filter.setCode(code);
			List<TournamentSO> result = tournamentService.find(filter);
			return ResponseEntity.ok(JSendResponse.success("tournaments",result));
		} catch (ServiceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSendResponse.error( new Long(HttpStatus.INTERNAL_SERVER_ERROR.value()),e.getMessage()));
		}
	}

	@GetMapping(value = "/{idTournament}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSendResponse> findById(@PathVariable("idTournament") Long id) {
		TournamentSO result = null;
		try {
			result = tournamentService.findById(id);
			return ResponseEntity.ok(JSendResponse.success("tournament",result));
		} catch (ServiceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSendResponse.error( new Long(HttpStatus.INTERNAL_SERVER_ERROR.value()),e.getMessage()));
		}
	}

	@GetMapping( value = "create/{gameType}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSendResponse> findById(@PathVariable("gameType") String gameType) {
		TournamentSO result = null;
		try {
			result = tournamentService.createTournament(gameType);
			return ResponseEntity.ok(JSendResponse.success("tournament",result));
		} catch (ServiceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSendResponse.error( new Long(HttpStatus.INTERNAL_SERVER_ERROR.value()),e.getMessage()));
		}
	}
	
	@PostMapping
	public ResponseEntity<JSendResponse> save(@RequestBody TournamentSO tournament) {
		try {
			TournamentSO tournamentUpdated = getTournamentService().save(tournament);
			return ResponseEntity.ok(JSendResponse.success("tournament",tournamentUpdated));
		}catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping(value = "/roundmatch")
	public ResponseEntity<JSendResponse> createRound(@RequestBody RoundMatchRequest request) {
		try {
			if (request.getTournament()==null) {
				return ResponseEntity.badRequest().body(JSendResponse.fail().setData("errors","Tournament is required"));
			}
			RoundMatchSO rmsio = getRoundMatchService().createNewRound(request.getTournament().getId());
			return ResponseEntity.ok(JSendResponse.success("round",rmsio));
		}catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping(value = "/roundmatch")
	public ResponseEntity<JSendResponse> updateRound(@RequestBody RoundMatchRequest request) {
		try {
			if (request.getTournament()==null) {
				return ResponseEntity.badRequest().body(JSendResponse.fail().setData("errors","Tournament is required"));
			}
			RoundMatchSO rmUpdated = getRoundMatchService().save(request.getRound());
			if (request.getMatches()!=null && !request.getMatches().isEmpty()) {
				for (MatchSO so  : request.getMatches()) {
					MatchSO mUpdated = matchService.save(so);
					rmUpdated.getMatches().add(mUpdated.getId());
				}
			}
			return ResponseEntity.ok(JSendResponse.success("round",rmUpdated));
		}catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
