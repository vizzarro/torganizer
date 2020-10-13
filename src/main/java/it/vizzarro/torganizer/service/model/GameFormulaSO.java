package it.vizzarro.torganizer.service.model;

import java.util.Date;

public class GameFormulaSO extends BaseDomainSO{

    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CODE = "code";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_GAME = "game";

    private String game;

    public GameFormulaSO() {
        super();
    }

    public GameFormulaSO(Long id, String code, String name, String game) {
        this();
        this.id = id;
        this.code = code;
        this.name = name;
        this.game = game;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }
}
