/**
 * 
 */
package it.vizzarro.torganizer.service;

import java.beans.Introspector;
import java.util.List;

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
public class TournamentServiceImpl extends CrudServiceImpl<Tournament,TournamentFilter,Tournament,Long> implements TournamentService{

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
	public List<Tournament> find(TournamentFilter filter) {
		
		return getTournamentRepo().findAll(new TournamentSpecification(filter));
	}

	@Override
	public Tournament findById(Long id) throws ServiceException{
		if (id != null) {
			return getTournamentRepo().findById(id).get();
		}else{
			ServiceException se = new ServiceException();
			throw se;
		}
	}

	public Tournament save(Tournament tournament ) {
		
		    Tournament tournamentOld = new Tournament();
			if (tournament.getId() != null) {
				tournamentOld = getTournamentRepo().findById(tournament.getId()).get();
			}
			
			populate(tournamentOld, tournament);
			
			return getTournamentRepo().save(tournamentOld);
	}




	@Override
	public void populate(Tournament target, Tournament source) {
		
		target.setId(source.getId());
		target.setCode(source.getCode());
		target.setName(source.getName());
		target.setBoards(source.getBoards());
		target.setBonus(source.getBonus());
		target.setDataCreation(source.getDataCreation());
		target.setgFormula(source.getgFormula());
		target.setMode(source.getMode());
		target.setReferee(source.getReferee());
		target.setSite(source.getSite());
		target.setTimes(source.getTimes());
		target.setType(source.getType());
	}




	@Override
	public List<Tournament> findAll() throws ServiceException {
		// TODO Auto-generated method stub
		return getTournamentRepo().findAll(new TournamentSpecification(null));
	}



	

}
