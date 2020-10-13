package it.vizzarro.torganizer.service.model;

import java.util.Set;

public class RoundMatchSO {


    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_TOURNAMENT = "tournament";
    public static final String PROPERTY_MATCHES = "matches";

    private Long id;
    private Long tournamentId;
    private Set<Long> matches;

    public RoundMatchSO() {
    }

    public RoundMatchSO(Long id, Long tournamentId, Set<Long> matches) {
        this.id = id;
        this.tournamentId = tournamentId;
        this.matches = matches;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public Set<Long> getMatches() {
        return matches;
    }

    public void setMatches(Set<Long> matches) {
        this.matches = matches;
    }
}
