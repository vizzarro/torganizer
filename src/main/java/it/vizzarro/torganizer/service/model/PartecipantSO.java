package it.vizzarro.torganizer.service.model;

public class PartecipantSO {
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_EMAIL = "email";
    public static final String PROPERTY_NOMINATIVE = "nominative";


    private Long id;
    private String email;
    private String nominative;

    public PartecipantSO() {
    }

    public PartecipantSO(Long id, String email, String nominative) {
        this.id = id;
        this.email = email;
        this.nominative = nominative;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNominative() {
        return nominative;
    }

    public void setNominative(String nominative) {
        this.nominative = nominative;
    }
}
