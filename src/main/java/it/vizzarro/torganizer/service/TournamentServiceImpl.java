/**
 * 
 */
package it.vizzarro.torganizer.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import it.vizzarro.torganizer.models.*;
import it.vizzarro.torganizer.repository.*;
import it.vizzarro.torganizer.service.model.TournamentSO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author Alessandro Vizzarro
 *
 */
@Service
public class TournamentServiceImpl extends CrudServiceImpl<TournamentSO,TournamentFilter,Tournament,Long> implements TournamentService{

	private TournamentRepo tournamentRepo;
	private GameFormulaRepo gameFormulaRepo;
	private ModeTournamentRepo modeTournamentRepo;

	
	/**
	 * 
	 */
	public TournamentServiceImpl(@Autowired TournamentRepo tournamentRepo,
								 @Autowired GameFormulaRepo gameFormulaRepo,
								 @Autowired ModeTournamentRepo modeTournamentRepo) {
		super();
		this.tournamentRepo = tournamentRepo;
		this.gameFormulaRepo = gameFormulaRepo;
		this.modeTournamentRepo = modeTournamentRepo;
	}
	
	


	public TournamentRepo getTournamentRepo() {
		return tournamentRepo;
	}




	@Override
	public List<TournamentSO> find(TournamentFilter filter) {

		return getTournamentRepo().findAll(new TournamentSpecification(filter)).stream().map(t -> toServiceObject(t)).collect(Collectors.toList());
	}

	@Override
	public TournamentSO findById(Long id) throws ServiceException{
		if (id != null) {
			return toServiceObject(getTournamentRepo().findById(id).get());
		}else{
			ServiceException se = new ServiceException();
			throw se;
		}
	}

	@Override
	public TournamentSO save(TournamentSO entityService) throws ServiceException {
		Tournament tournamentOld = new Tournament();
		if (entityService.getId() != null) {
			tournamentOld = getTournamentRepo().findById(entityService.getId()).get();
		}

		populate(tournamentOld, entityService);

		return  toServiceObject(getTournamentRepo().save(tournamentOld));
	}






	@Override
	public void populate(Tournament target, TournamentSO source) throws ServiceException {
		super.populate(target,source);
	}

	@Override
	protected void validate(Tournament target, TournamentSO source) throws ServiceException {

	}


	@Override
	public List<TournamentSO> findAll() throws ServiceException {
		return getTournamentRepo().findAll(new TournamentSpecification(null)).stream().map(t -> toServiceObject(t)).collect(Collectors.toList());
	}


	@Override
	protected void populateProperty(String name, Tournament target, TournamentSO source) {
		switch (name) {
			case Tournament.PROPERTY_MODE:
				ModeTournament mt = modeTournamentRepo.findById(source.getMode()).get();
				target.setMode(mt);
				break;
			case Tournament.PROPERTY_TYPE:
				if (source.getType()!=null){
					target.setType(TypeTournament.valueOf(source.getType()));
				}
				break;
			case Tournament.PROPERTY_GAME_FORMULA:
				GameFormula gf = gameFormulaRepo.findById(source.getGameFormula()).get();
				target.setGameFormula(gf);
				break;
			case Tournament.PROPERTY_GAME:
				if (source.getGame()!=null) {
					target.setGame(Game.valueOf(source.getGame()));
				}
				break;
			case Tournament.PROPERTY_STATUS:
				if (source.getStatus()!=null) {
					target.setStatus(TournamentStatus.valueOf(source.getStatus()));
				}
				break;
		}
	}

	@Override
	public TournamentSO toServiceObject(Tournament source) {
		TournamentSO so = new TournamentSO();
		so.setId(source.getId());
		so.setCode(source.getCode());
		so.setName(source.getName());
		so.setBoards(source.getBoards());
		so.setBonus(source.getBonus());
		so.setDataCreation(source.getDataCreation());
		if (source.getGameFormula()!=null) so.setGameFormula(source.getGameFormula().getId());
		if (source.getMode()!=null) so.setMode(source.getMode().getId());
		so.setReferee(source.getReferee());
		so.setSite(source.getSite());
		so.setTimes(source.getTimes());
		if (source.getType()!=null) so.setType(source.getType().name());
		if (source.getGame()!=null) so.setGame(source.getGame().name());
		if (source.getStatus()!=null) so.setStatus(source.getStatus().name());

		return so;
	}

	@Override
	public TournamentSO createTournament(String gameType) throws ServiceException {

		Tournament t = new Tournament(null, UUID.randomUUID().toString(),null,null,null,null,null,null,1,1,false,new Date(),Game.valueOf(gameType));
		t = getTournamentRepo().save(t);
		return toServiceObject(t);
	}
}
