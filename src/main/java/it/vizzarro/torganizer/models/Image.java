package it.vizzarro.torganizer.models;

import javax.persistence.*;

/**
 * @author Alessandro Vizzarro
 */
@Entity(name = "Image")
@SequenceGenerator(name="ImageSeq",sequenceName ="Image_SEQ", initialValue=1, allocationSize=1)
public class Image {

    private Long id;
    private String description;
    private byte[] defaultSmall; // Icon Image
    private byte[] defaultLarge; // Normal Image
    private String urlSmall;
    private String urlLarge;

    public Image() {
    }

    public Image(Long id, String description, byte[] defaultSmall, byte[] defaultLarge, String urlSmall, String urlLarge) {
        this.id = id;
        this.description = description;
        this.defaultSmall = defaultSmall;
        this.defaultLarge = defaultLarge;
        this.urlSmall = urlSmall;
        this.urlLarge = urlLarge;
    }

    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="ImageSeq")
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getDefaultSmall() {
        return defaultSmall;
    }

    public void setDefaultSmall(byte[] defaultSmall) {
        this.defaultSmall = defaultSmall;
    }

    public byte[] getDefaultLarge() {
        return defaultLarge;
    }

    public void setDefaultLarge(byte[] defaultLarge) {
        this.defaultLarge = defaultLarge;
    }

    public String getUrlSmall() {
        return urlSmall;
    }

    public void setUrlSmall(String urlSmall) {
        this.urlSmall = urlSmall;
    }

    public String getUrlLarge() {
        return urlLarge;
    }

    public void setUrlLarge(String urlLarge) {
        this.urlLarge = urlLarge;
    }
}
