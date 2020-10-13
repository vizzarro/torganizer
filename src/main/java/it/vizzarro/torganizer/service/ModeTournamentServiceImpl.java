/**
 * 
 */
package it.vizzarro.torganizer.service;

import it.vizzarro.torganizer.models.Game;
import it.vizzarro.torganizer.models.GameFormula;
import it.vizzarro.torganizer.models.ModeTournament;
import it.vizzarro.torganizer.models.Tournament;
import it.vizzarro.torganizer.repository.*;
import it.vizzarro.torganizer.service.model.GameFormulaSO;
import it.vizzarro.torganizer.service.model.ModeTournamentSO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Alessandro Vizzarro
 *
 */
@Service
public class ModeTournamentServiceImpl extends CrudServiceImpl<ModeTournamentSO, ModeTournamentFilter, ModeTournament,Long> implements ModeTournamentService{

	private ModeTournamentRepo modeTournamentRepo;


	/**
	 *
	 */
	public ModeTournamentServiceImpl(@Autowired ModeTournamentRepo modeTournamentRepo) {
		super();
		this.modeTournamentRepo = modeTournamentRepo;
	}

	public ModeTournamentRepo getModeTournamentRepo() {
		return modeTournamentRepo;
	}

	@Override
	public List<ModeTournamentSO> find(ModeTournamentFilter filter) {

		return getModeTournamentRepo().findAll(new ModeTournamentSpecification(filter)).stream().map(t -> toServiceObject(t)).collect(Collectors.toList());
	}

	@Override
	public ModeTournamentSO findById(Long id) throws ServiceException{
		if (id != null) {
			return toServiceObject(getModeTournamentRepo().findById(id).get());
		}else{
			ServiceException se = new ServiceException();
			throw se;
		}
	}

	@Override
	public ModeTournamentSO save(ModeTournamentSO entityService) throws ServiceException {
		ModeTournament mtOld = new ModeTournament();
		if (entityService.getId() != null) {
			mtOld = getModeTournamentRepo().findById(entityService.getId()).get();
		}

		populate(mtOld, entityService);

		return  toServiceObject(getModeTournamentRepo().save(mtOld));
	}






	@Override
	public void populate(ModeTournament target, ModeTournamentSO source) throws ServiceException {
		super.populate(target,source);
	}

	@Override
	protected void validate(ModeTournament target, ModeTournamentSO source) throws ServiceException {

	}


	@Override
	public List<ModeTournamentSO> findAll() throws ServiceException {
		return getModeTournamentRepo().findAll(new ModeTournamentSpecification(null)).stream().map(t -> toServiceObject(t)).collect(Collectors.toList());
	}


	@Override
	protected void populateProperty(String name, ModeTournament target, ModeTournamentSO source) {
		switch (name) {

			case ModeTournamentSO.PROPERTY_GAME:
				if (source.getGame()!=null) {
					target.setGame(Game.valueOf(source.getGame()));
				}
				break;
		}
	}

	@Override
	public ModeTournamentSO toServiceObject(ModeTournament source) {
		ModeTournamentSO so = new ModeTournamentSO();
		so.setId(source.getId());
		so.setCode(source.getCode());
		so.setName(source.getName());
		so.setDescription(source.getDescription());
		if (source.getGame()!=null) so.setGame(source.getGame().name());


		return so;
	}
}
