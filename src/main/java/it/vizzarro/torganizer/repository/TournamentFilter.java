/**
 * 
 */
package it.vizzarro.torganizer.repository;

import java.util.Date;

/**
 * @author Utente
 *
 */
public class TournamentFilter {
	
	private String name;
	private String code;
	private Date dateCreation;
	
	/**
	 * 
	 */
	public TournamentFilter() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	
	
}
