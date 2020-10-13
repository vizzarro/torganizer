package it.vizzarro.torganizer.models;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "ROUND_MATCH")
@SequenceGenerator(name="RoundMatchSeq",sequenceName ="ROUND_MATCH_SEQ", initialValue=1, allocationSize=1)
public class RoundMatch {

    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_TOURNAMENT = "tournament";
    public static final String PROPERTY_MATCHES = "matches";

    private Long id;
    private Tournament tournament;
    private Set<Match> matches ;
    private Integer roundNumber;

    public RoundMatch() {
    }

    public RoundMatch(Long id, Tournament tournament, Set<Match> matches) {
        this.id = id;
        this.tournament = tournament;
        this.matches = matches;
    }

    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="RoundMatchSeq")
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id")
    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "round",cascade = CascadeType.ALL)
    public Set<Match> getMatches() {
        return matches;
    }

    public void setMatches(Set<Match> matches) {
        this.matches = matches;
    }

    public Integer getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(Integer roundNumber) {
        this.roundNumber = roundNumber;
    }
}
