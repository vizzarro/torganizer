package it.vizzarro.torganizer.service.model;

import it.vizzarro.torganizer.models.GameFormula;
import it.vizzarro.torganizer.models.ModeTournament;
import it.vizzarro.torganizer.models.TypeTournament;

import javax.persistence.*;
import java.util.Date;

public class TournamentSO {

    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CODE = "code";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_TYPE = "gFormula";
    public static final String PROPERTY_REFEREE = "referee";
    public static final String PROPERTY_SITE = "site";
    public static final String PROPERTY_TIMES = "times";
    public static final String PROPERTY_BOARDS = "boards";
    public static final String PROPERTY_BONUS = "bonus";
    public static final String PROPERTY_DATA_CREATION = "dataCreation";

    private Long id;
    private String code;
    private String name;
    private TypeTournament type;
    private ModeTournament mode;
    private GameFormula gFormula;
    private String referee;
    private String site;
    private Integer times;
    private Integer boards;
    private Boolean bonus;
    private Date dataCreation;



    public TournamentSO() {
        super();
    }



    public TournamentSO(Long id, String code, String name, TypeTournament type, ModeTournament mode, GameFormula gFormula,
                      String referee, String site, Integer times, Integer boards, Boolean bonus, Date dataCreation) {
        this();
        this.id = id;
        this.code = code;
        this.name = name;
        this.type = type;
        this.mode = mode;
        this.gFormula = gFormula;
        this.referee = referee;
        this.site = site;
        this.times = times;
        this.boards = boards;
        this.bonus = bonus;
        this.dataCreation = dataCreation;
    }

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



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }


    public TypeTournament getType() {
        return type;
    }



    public void setType(TypeTournament type) {
        this.type = type;
    }



    public ModeTournament getMode() {
        return mode;
    }



    public void setMode(ModeTournament mode) {
        this.mode = mode;
    }



    public GameFormula getgFormula() {
        return gFormula;
    }



    public void setgFormula(GameFormula gFormula) {
        this.gFormula = gFormula;
    }



    public String getReferee() {
        return referee;
    }



    public void setReferee(String referee) {
        this.referee = referee;
    }



    public String getSite() {
        return site;
    }



    public void setSite(String site) {
        this.site = site;
    }



    public Integer getTimes() {
        return times;
    }



    public void setTimes(Integer times) {
        this.times = times;
    }



    public Integer getBoards() {
        return boards;
    }



    public void setBoards(Integer boards) {
        this.boards = boards;
    }



    public Boolean getBonus() {
        return bonus;
    }



    public void setBonus(Boolean bonus) {
        this.bonus = bonus;
    }



    public Date getDataCreation() {
        return dataCreation;
    }



    public void setDataCreation(Date dataCreation) {
        this.dataCreation = dataCreation;
    }
}
