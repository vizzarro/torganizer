package it.vizzarro.torganizer.models;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "TEAM")
@SequenceGenerator(name="TeamSeq",sequenceName ="TEAM_SEQ", initialValue=1, allocationSize=1)
public class Team {

    private Long id;
    private String code;
    private TeamType type;
    private Tournament tournament;
    private Set<Partecipant> partecipants;

    public Team() {
    }

    public Team(Long id, String code, TeamType type, Tournament tournament) {
        this.id = id;
        this.code = code;
        this.type = type;
        this.tournament = tournament;
    }

    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="TeamSeq")
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Enumerated(EnumType.STRING)
    public TeamType getType() {
        return type;
    }

    public void setType(TeamType type) {
        this.type = type;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id")
    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    public Set<Partecipant> getPartecipants() {
        return partecipants;
    }

    public void setPartecipants(Set<Partecipant> partecipants) {
        this.partecipants = partecipants;
    }
}
