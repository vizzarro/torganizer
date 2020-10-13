package it.vizzarro.torganizer.models;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public abstract class BaseDomain {

    protected Long id;
    protected String code;
    protected String name;
    protected String description;
    protected Image image;

    public BaseDomain() {
    }

    public BaseDomain(Long id, String code, String name, String description,Image image) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract Long getId();

    public abstract String getCode();

    public abstract String getName();

    public abstract String getDescription();

    public abstract Image getImage() ;

    public void setImage(Image image) {
        this.image = image;
    }
}
