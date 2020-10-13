package it.vizzarro.torganizer.service;

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
public class RoundMatchServiceImpl extends CrudServiceImpl<RoundMatchSO, RoundMatchFilter, RoundMatch,Long>  implements RoundMatchService{

    private RoundMatchRepo roundMatchRepo;
    private TournamentRepo tournamentRepo;
    private MatchRepo matchRepo;

    public RoundMatchServiceImpl(@Autowired RoundMatchRepo roundMatchRepo,
                                 @Autowired TournamentRepo tournamentRepo,
                                 @Autowired MatchRepo matchRepo) {
        this.roundMatchRepo = roundMatchRepo;
        this.tournamentRepo = tournamentRepo;
        this.matchRepo = matchRepo;
    }

    public RoundMatchRepo getRoundMatchRepo() {
        return roundMatchRepo;
    }

    public TournamentRepo getTournamentRepo() {
        return tournamentRepo;
    }

    public MatchRepo getMatchRepo() {
        return matchRepo;
    }

    @Override
    protected void validate(RoundMatch target, RoundMatchSO source) throws ServiceException {

    }

    @Override
    protected void populateProperty(String name, RoundMatch target, RoundMatchSO source) {

        switch (name) {
            case RoundMatchSO.PROPERTY_TOURNAMENT: {
                Tournament t = getTournamentRepo().findById(source.getTournamentId()).get();
                target.setTournament(t);
                break;
            }
            case RoundMatchSO.PROPERTY_MATCHES: {
                if (source.getMatches()!=null && !source.getMatches().isEmpty()) {
                    target.setMatches(source.getMatches().stream().map(m -> getMatchRepo().findById(m).get()).collect(Collectors.toSet()));
                }
            }
        }
    }

    @Override
    public RoundMatchSO toServiceObject(RoundMatch source) {
        RoundMatchSO so  = new RoundMatchSO();
        so.setId(source.getId());
        so.setTournamentId(source.getTournament().getId());
        so.setMatches(source.getMatches().stream().map(rm -> rm.getId()).collect(Collectors.toSet()));
        return so;
    }

    @Override
    public RoundMatchSO
    createNewRound(Long tournamentId) throws ServiceException {
        RoundMatch newRm = new RoundMatch();
        newRm.setTournament(getTournamentRepo().findById(tournamentId).get());
        if (newRm.getMatches() == null || newRm.getMatches().isEmpty()) {
            newRm.setRoundNumber(1);
        }else{
            newRm.setRoundNumber(newRm.getMatches().size()+1);
        }
        return toServiceObject(getRoundMatchRepo().save(newRm));
    }


    @Override
    public List<RoundMatchSO> find(RoundMatchFilter filter) throws ServiceException {
       return getRoundMatchRepo().findAll(new RoundMatchSpecification(filter)).stream().map(e -> toServiceObject(e)).collect(Collectors.toList());
    }

    @Override
    public List<RoundMatchSO> findAll() throws ServiceException {
       throw  new ServiceException("Not Implemented!!!");
    }


    @Override
    public RoundMatchSO findById(Long id) throws ServiceException {
        return toServiceObject(getRoundMatchRepo().findById(id).get());
    }

    @Override
    public RoundMatchSO save(RoundMatchSO entityService) throws ServiceException {
        RoundMatch rmOld = getRoundMatchRepo().findById(entityService.getId()).get();
        validate(rmOld,entityService);
        populate(rmOld,entityService);
        return toServiceObject(getRoundMatchRepo().save(rmOld));
    }

    public RoundMatchSO associateMatch(RoundMatchSO entityService, Set<MatchSO> matchesSO) throws ServiceException {
        RoundMatch rmOld = getRoundMatchRepo().findById(entityService.getId()).get();

        return toServiceObject(getRoundMatchRepo().save(rmOld));
    }
}
