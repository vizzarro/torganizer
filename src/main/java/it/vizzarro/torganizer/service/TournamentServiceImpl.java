/**
 * 
 */
package it.vizzarro.torganizer.service;

import java.util.List;
import java.util.stream.Collectors;

import it.vizzarro.torganizer.service.model.TournamentSO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.vizzarro.torganizer.models.Tournament;
import it.vizzarro.torganizer.repository.TournamentFilter;
import it.vizzarro.torganizer.repository.TournamentRepo;
import it.vizzarro.torganizer.repository.TournamentSpecification;



/**
 * @author Alessandro Vizzarro
 *
 */
@Service
public class TournamentServiceImpl extends CrudServiceImpl<TournamentSO,TournamentFilter,Tournament,Long> implements TournamentService{

	private TournamentRepo tournamentRepo;

	
	/**
	 * 
	 */
	public TournamentServiceImpl(@Autowired TournamentRepo tournamentRepo) {
		super();
		this.tournamentRepo = tournamentRepo;
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
				target.setMode(source.getMode());
				break;
			case Tournament.PROPERTY_TYPE:
				target.setType(source.getType());
				break;
			case Tournament.PROPERTY_GAME_FORMULA:
				target.setgFormula(source.getgFormula());
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
		so.setgFormula(source.getgFormula());
		so.setMode(source.getMode());
		so.setReferee(source.getReferee());
		so.setSite(source.getSite());
		so.setTimes(source.getTimes());
		so.setType(source.getType());

		return so;
	}
}
