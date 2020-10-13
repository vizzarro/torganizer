package it.vizzarro.torganizer.repository;

import it.vizzarro.torganizer.service.BaseFilter;
import it.vizzarro.torganizer.service.ServiceException;

public class ModeTournamentFilter extends BaseFilter {

    private String game;

    public ModeTournamentFilter(String q) throws ServiceException {
        super(q);
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }
}
