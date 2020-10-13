/**
 * 
 */
package it.vizzarro.torganizer.repository;

import it.vizzarro.torganizer.service.BaseFilter;
import it.vizzarro.torganizer.service.ServiceException;

import java.util.Date;

/**
 * @author Alessandro Vizzarro
 *
 */
public class RoundMatchFilter extends BaseFilter{

	private Long tournamentId;


	public RoundMatchFilter(String q) throws ServiceException {
		super(q);
	}

	public Long getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(Long tournamentId) {
		this.tournamentId = tournamentId;
	}
}
