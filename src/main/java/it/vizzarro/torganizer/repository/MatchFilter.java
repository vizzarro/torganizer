/**
 * 
 */
package it.vizzarro.torganizer.repository;

import it.vizzarro.torganizer.service.BaseFilter;
import it.vizzarro.torganizer.service.ServiceException;

/**
 * @author Alessandro Vizzarro
 *
 */
public class MatchFilter extends BaseFilter{

	private Long roundId;


	public MatchFilter(String q) throws ServiceException {
		super(q);
	}

	public Long getRoundId() {
		return roundId;
	}

	public void setRoundId(Long roundId) {
		this.roundId = roundId;
	}
}
