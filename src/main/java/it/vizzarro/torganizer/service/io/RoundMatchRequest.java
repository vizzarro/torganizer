package it.vizzarro.torganizer.service.io;

import it.vizzarro.torganizer.service.model.MatchSO;
import it.vizzarro.torganizer.service.model.RoundMatchSO;
import it.vizzarro.torganizer.service.model.TournamentSO;

import java.util.Set;

public class RoundMatchRequest {

    private TournamentSO tournament;
    private RoundMatchSO round;
    private Set<MatchSO> matches;

    public TournamentSO getTournament() {
        return tournament;
    }

    public void setTournament(TournamentSO tournament) {
        this.tournament = tournament;
    }

    public RoundMatchSO getRound() {
        return round;
    }

    public void setRound(RoundMatchSO round) {
        this.round = round;
    }

    public Set<MatchSO> getMatches() {
        return matches;
    }

    public void setMatches(Set<MatchSO> matches) {
        this.matches = matches;
    }
}
