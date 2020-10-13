package it.vizzarro.torganizer.service.model;

import it.vizzarro.torganizer.models.Game;
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
    public static final String PROPERTY_GAME = "game";

    private Long id;
    private String code;
    private String name;
    private String type;
    private Long mode;
    private Long gameFormula;
    private String referee;
    private String site;
    private Integer times;
    private Integer boards;
    private Boolean bonus;
    private Date dataCreation;
    private String game;



    public TournamentSO() {
        super();
    }



    public TournamentSO(Long id, String code, String name, String type, Long mode, Long gameFormula,
                      String referee, String site, Integer times, Integer boards, Boolean bonus, Date dataCreation,String game) {
        this();
        this.id = id;
        this.code = code;
        this.name = name;
        this.type = type;
        this.mode = mode;
        this.gameFormula = gameFormula;
        this.referee = referee;
        this.site = site;
        this.times = times;
        this.boards = boards;
        this.bonus = bonus;
        this.dataCreation = dataCreation;
        this.game = game;
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


    public String getType() {
        return type;
    }



    public void setType(String type) {
        this.type = type;
    }



    public Long getMode() {
        return mode;
    }



    public void setMode(Long mode) {
        this.mode = mode;
    }



    public Long getGameFormula() {
        return gameFormula;
    }



    public void setGameFormula(Long gameFormula) {
        this.gameFormula = gameFormula;
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

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }
}
