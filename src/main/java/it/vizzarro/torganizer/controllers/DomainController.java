package it.vizzarro.torganizer.controllers;

import it.vizzarro.torganizer.repository.GameFormulaFilter;
import it.vizzarro.torganizer.repository.ModeTournamentFilter;
import it.vizzarro.torganizer.repository.TournamentFilter;
import it.vizzarro.torganizer.service.GameFormulaService;
import it.vizzarro.torganizer.service.ModeTournamentService;
import it.vizzarro.torganizer.service.ServiceException;
import it.vizzarro.torganizer.service.TournamentService;
import it.vizzarro.torganizer.service.model.GameFormulaSO;
import it.vizzarro.torganizer.service.model.ModeTournamentSO;
import it.vizzarro.torganizer.service.model.TournamentSO;
import it.vizzarro.torganizer.utils.jsend.JSendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Alessandro Vizzarro
 *
 */
@RestController
@RequestMapping("/api/domain")
public class DomainController {

    private static final Logger logger = LoggerFactory.getLogger(DomainController.class);

    private GameFormulaService gameFormulaService;
    private ModeTournamentService modeTournamentService;

    public DomainController(@Autowired GameFormulaService gameFormulaService,@Autowired ModeTournamentService modeTournamentService) {
        super();
        this.gameFormulaService = gameFormulaService;
        this.modeTournamentService  =modeTournamentService;
    }

    public GameFormulaService getGameFormulaService() {
        return gameFormulaService;
    }

    public ModeTournamentService getModeTournamentService() {
        return modeTournamentService;
    }

    @GetMapping(value = "/gameFormula", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSendResponse> findGameFormula(@Param("q") String q) {
        try {
            GameFormulaFilter filter = new GameFormulaFilter(q);
            List<GameFormulaSO> result = getGameFormulaService().find(filter);
            return ResponseEntity.ok(JSendResponse.success("gameformulas",result));
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSendResponse.error( new Long(HttpStatus.INTERNAL_SERVER_ERROR.value()),e.getMessage()));
        }
    }

    @GetMapping(value = "/modeTournament", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSendResponse> findModeTournaments(@Param("q") String q) {
        try {
            ModeTournamentFilter filter = new ModeTournamentFilter(q);
            List<ModeTournamentSO> result = getModeTournamentService().find(filter);
            return ResponseEntity.ok(JSendResponse.success("modetournaments",result));
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSendResponse.error( new Long(HttpStatus.INTERNAL_SERVER_ERROR.value()),e.getMessage()));
        }
    }
}
