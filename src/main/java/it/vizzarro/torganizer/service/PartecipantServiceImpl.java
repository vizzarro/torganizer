/**
 * 
 */
package it.vizzarro.torganizer.service;

import it.vizzarro.torganizer.models.*;
import it.vizzarro.torganizer.repository.*;
import it.vizzarro.torganizer.service.model.PartecipantSO;
import it.vizzarro.torganizer.service.model.TournamentSO;
import javafx.scene.shape.PathElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * @author Alessandro Vizzarro
 *
 */
@Service
public class PartecipantServiceImpl extends CrudServiceImpl<PartecipantSO,PartecipantFilter,Partecipant,Long> implements PartecipantService{

	private PartecipantRepo partecipantRepo;


	/**
	 *
	 */
	public PartecipantServiceImpl(@Autowired PartecipantRepo partecipantRepo) {
		super();
		this.partecipantRepo = partecipantRepo;
	}
	
	


	public PartecipantRepo getPartecipantRepo() {
		return partecipantRepo;
	}




	@Override
	public List<PartecipantSO> find(PartecipantFilter filter) {

		return getPartecipantRepo().findAll(new PartecipantSpecification(filter)).stream().map(t -> toServiceObject(t)).collect(Collectors.toList());
	}

	@Override
	public PartecipantSO findById(Long id) throws ServiceException{
		if (id != null) {
			return toServiceObject(getPartecipantRepo().findById(id).get());
		}else{
			ServiceException se = new ServiceException();
			throw se;
		}
	}

	@Override
	public PartecipantSO save(PartecipantSO entityService) throws ServiceException {
		Partecipant pOld = new Partecipant();
		if (entityService.getId() != null) {
			pOld = getPartecipantRepo().findById(entityService.getId()).get();
		}

		populate(pOld, entityService);

		return  toServiceObject(getPartecipantRepo().save(pOld));
	}






	@Override
	public void populate(Partecipant target, PartecipantSO source) throws ServiceException {
		super.populate(target,source);
	}

	@Override
	protected void validate(Partecipant target, PartecipantSO source) throws ServiceException {

	}


	@Override
	public List<PartecipantSO> findAll() throws ServiceException {
		return getPartecipantRepo().findAll(new PartecipantSpecification(null)).stream().map(t -> toServiceObject(t)).collect(Collectors.toList());
	}


	@Override
	protected void populateProperty(String name, Partecipant target, PartecipantSO source) {


	}

	@Override
	public PartecipantSO toServiceObject(Partecipant source) {
		PartecipantSO so = new PartecipantSO();
		so.setId(source.getId());
		so.setEmail(source.getEmail());
		so.setNominative(source.getNominative());

		return so;
	}

	@Override
	public PartecipantSO createPartecipant() throws ServiceException {

		Partecipant t = new Partecipant(null, null, null);
		t = getPartecipantRepo().save(t);
		return toServiceObject(t);
	}
}
