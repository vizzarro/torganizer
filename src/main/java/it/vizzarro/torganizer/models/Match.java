package it.vizzarro.torganizer.models;

import javax.persistence.*;

@Entity(name = "RMATCH")
@SequenceGenerator(name="MatchSeq",sequenceName ="RMATCH_SEQ", initialValue=1, allocationSize=1)
public class Match {


    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_FIRST_TEAM = "firstTeam";
    public static final String PROPERTY_MSECOND_TEAM = "secondTeam";

    private Long id;

    private RoundMatch round;

    private Team firstTeam;

    private Team secondTeam;

    public Match() {
        super();
    }

    public Match(Long id, RoundMatch round, Team firstTeam, Team secondTeam) {
        this();
        this.id = id;
        this.round = round;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }

    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="MatchSeq")
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "first_team_id")
    public Team getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(Team firstTeam) {
        this.firstTeam = firstTeam;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "second_team_id")
    public Team getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(Team secondTeam) {
        this.secondTeam = secondTeam;
    }

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "round_id")
    public RoundMatch getRound() {
        return round;
    }

    public void setRound(RoundMatch round) {
        this.round = round;
    }
}
