package it.vizzarro.torganizer.service;


import it.vizzarro.torganizer.models.Match;
import it.vizzarro.torganizer.models.RoundMatch;
import it.vizzarro.torganizer.repository.MatchFilter;
import it.vizzarro.torganizer.repository.RoundMatchFilter;
import it.vizzarro.torganizer.service.model.MatchSO;
import it.vizzarro.torganizer.service.model.RoundMatchSO;

public interface MatchService extends CrudService<MatchSO, MatchFilter, Match,Long> {


}
