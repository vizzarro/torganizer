/**
 * 
 */
package it.vizzarro.torganizer.controllers;

import it.vizzarro.torganizer.repository.PartecipantFilter;
import it.vizzarro.torganizer.repository.TournamentFilter;
import it.vizzarro.torganizer.service.*;
import it.vizzarro.torganizer.service.io.RoundMatchRequest;
import it.vizzarro.torganizer.service.model.MatchSO;
import it.vizzarro.torganizer.service.model.PartecipantSO;
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
@RequestMapping("/api/partecipant")
public class PartecipantController {

	private static final Logger logger = LoggerFactory.getLogger(PartecipantController.class);

	private PartecipantService partecipantService;


	public PartecipantController(@Autowired PartecipantService partecipantService) {
		super();
		this.partecipantService = partecipantService;
	}

	public PartecipantService getPartecipantService() {
		return partecipantService;
	}

	public void setPartecipantService(PartecipantService partecipantService) {
		this.partecipantService = partecipantService;
	}


	@GetMapping
	public ResponseEntity<JSendResponse> find(@Param("q") String q) {
		try {
			PartecipantFilter filter = new PartecipantFilter(q);
			List<PartecipantSO> result = getPartecipantService().find(filter);
			return ResponseEntity.ok(JSendResponse.success("partecipants",result));
		} catch (ServiceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSendResponse.error( new Long(HttpStatus.INTERNAL_SERVER_ERROR.value()),e.getMessage()));
		}
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSendResponse> findById(@PathVariable("id") Long id) {
		PartecipantSO result = null;
		try {
			result = getPartecipantService().findById(id);
			return ResponseEntity.ok(JSendResponse.success("partecipant",result));
		} catch (ServiceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSendResponse.error( new Long(HttpStatus.INTERNAL_SERVER_ERROR.value()),e.getMessage()));
		}
	}

	@GetMapping( value = "create",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSendResponse> create() {
		PartecipantSO result = null;
		try {
			result = getPartecipantService().createPartecipant();
			return ResponseEntity.ok(JSendResponse.success("partecipant",result));
		} catch (ServiceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSendResponse.error( new Long(HttpStatus.INTERNAL_SERVER_ERROR.value()),e.getMessage()));
		}
	}
	
	@PostMapping
	public ResponseEntity<JSendResponse> insert(@RequestBody PartecipantSO partecipant) {
		try {
			PartecipantSO pUpdated = getPartecipantService().save(partecipant);
			return ResponseEntity.ok(JSendResponse.success("partecipant",pUpdated));
		}catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping
	public ResponseEntity<JSendResponse> save(@RequestBody PartecipantSO partecipant) {
		try {
			PartecipantSO pUpdated = getPartecipantService().save(partecipant);
			return ResponseEntity.ok(JSendResponse.success("partecipant",pUpdated));
		}catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


}
