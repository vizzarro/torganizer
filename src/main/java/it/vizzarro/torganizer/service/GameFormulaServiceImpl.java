/**
 * 
 */
package it.vizzarro.torganizer.service;

import it.vizzarro.torganizer.models.*;
import it.vizzarro.torganizer.repository.*;
import it.vizzarro.torganizer.service.model.GameFormulaSO;
import it.vizzarro.torganizer.service.model.TournamentSO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Alessandro Vizzarro
 *
 */
@Service
public class GameFormulaServiceImpl extends CrudServiceImpl<GameFormulaSO,GameFormulaFilter,GameFormula,Long> implements GameFormulaService{

	private GameFormulaRepo gameFormulaRepo;




	/**
	 *
	 */
	public GameFormulaServiceImpl( @Autowired GameFormulaRepo gameFormulaRepo) {
		super();
		this.gameFormulaRepo = gameFormulaRepo;

	}

	public GameFormulaRepo getGameFormulaRepo() {
		return gameFormulaRepo;
	}



	@Override
	public List<GameFormulaSO> find(GameFormulaFilter filter) {

		return getGameFormulaRepo().findAll(new GameFormulaSpecification(filter)).stream().map(t -> toServiceObject(t)).collect(Collectors.toList());
	}

	@Override
	public GameFormulaSO findById(Long id) throws ServiceException{
		if (id != null) {
			return toServiceObject(getGameFormulaRepo().findById(id).get());
		}else{
			ServiceException se = new ServiceException();
			throw se;
		}
	}

	@Override
	public GameFormulaSO save(GameFormulaSO entityService) throws ServiceException {
		GameFormula gfOld = new GameFormula();
		if (entityService.getId() != null) {
			gfOld = getGameFormulaRepo().findById(entityService.getId()).get();
		}

		populate(gfOld, entityService);

		return  toServiceObject(getGameFormulaRepo().save(gfOld));
	}






	@Override
	public void populate(GameFormula target, GameFormulaSO source) throws ServiceException {
		super.populate(target,source);
	}

	@Override
	protected void validate(GameFormula target, GameFormulaSO source) throws ServiceException {

	}


	@Override
	public List<GameFormulaSO> findAll() throws ServiceException {
		return getGameFormulaRepo().findAll(new GameFormulaSpecification(null)).stream().map(t -> toServiceObject(t)).collect(Collectors.toList());
	}


	@Override
	protected void populateProperty(String name, GameFormula target, GameFormulaSO source) {
		switch (name) {

			case Tournament.PROPERTY_GAME:
				if (source.getGame()!=null) {
					target.setGame(Game.valueOf(source.getGame()));
				}
				break;
		}
	}

	@Override
	public GameFormulaSO toServiceObject(GameFormula source) {
		GameFormulaSO so = new GameFormulaSO();
		so.setId(source.getId());
		so.setCode(source.getCode());
		so.setName(source.getName());
		so.setDescription(source.getDescription());
		if (source.getGame()!=null) so.setGame(source.getGame().name());


		return so;
	}
}
