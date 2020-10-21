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
public class PartecipantFilter extends BaseFilter{

	private String nominative;
	private String email;


	public PartecipantFilter(String q) throws ServiceException {
		super(q);
	}

	public String getNominative() {
		return nominative;
	}

	public void setNominative(String nominative) {
		this.nominative = nominative;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
