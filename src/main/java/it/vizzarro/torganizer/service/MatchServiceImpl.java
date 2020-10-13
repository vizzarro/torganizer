package it.vizzarro.torganizer.service;

import it.vizzarro.torganizer.models.Match;
import it.vizzarro.torganizer.models.RoundMatch;
import it.vizzarro.torganizer.models.Tournament;
import it.vizzarro.torganizer.repository.*;
import it.vizzarro.torganizer.service.model.MatchSO;
import it.vizzarro.torganizer.service.model.RoundMatchSO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl extends CrudServiceImpl<MatchSO, MatchFilter, Match,Long>  implements MatchService{


    private MatchRepo matchRepo;

    public MatchServiceImpl(@Autowired MatchRepo matchRepo) {

        this.matchRepo = matchRepo;
    }

    public MatchRepo getMatchRepo() {
        return matchRepo;
    }


    @Override
    protected void validate(Match target, MatchSO source) throws ServiceException {

    }

    @Override
    protected void populateProperty(String name, Match target, MatchSO source) {

    }

    @Override
    public MatchSO toServiceObject(Match source) {
        return null;
    }

    @Override
    public List<MatchSO> find(MatchFilter filter) throws ServiceException {
        return null;
    }

    @Override
    public List<MatchSO> findAll() throws ServiceException {
        return null;
    }

    @Override
    public MatchSO findById(Long id) throws ServiceException {
        return null;
    }

    @Override
    public MatchSO save(MatchSO entityService) throws ServiceException {
        Match mOld = getMatchRepo().findById(entityService.getId()).get();
        validate(mOld,entityService);
        populate(mOld,entityService);
        return toServiceObject(getMatchRepo().save(mOld));
    }
}
