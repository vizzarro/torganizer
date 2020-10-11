package it.vizzarro.torganizer.models;

import javax.persistence.*;

@Entity(name = "TOURNAMENT_PARTECIPANT")
@SequenceGenerator(name="PartecipantSeq",sequenceName ="TOURNAMENT_PARTECIPIANT_SEQ", initialValue=1, allocationSize=1)
public class Partecipant {

    private Long id;
    private String nominative;
    private String email;

    public Partecipant() {
        super();
    }

    public Partecipant(Long id, String nominative, String email) {
        this();
        this.id = id;
        this.nominative = nominative;
        this.email = email;
    }

    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="PartecipantSeq")
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNominative() {
        return nominative;
    }

    public void setNominative(String nominative) {
        this.nominative = nominative;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
