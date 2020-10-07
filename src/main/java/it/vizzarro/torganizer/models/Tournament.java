/**
 * 
 */
package it.vizzarro.torganizer.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


/**
 * @author Alessandro Vizzarro
 *
 */
@Entity(name = "TOURNAMENT")
@SequenceGenerator(name="TournamentSeq", initialValue=1, allocationSize=50)
public class Tournament  {

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
	
	
	
	public Tournament() {
		super();
	}



	public Tournament(Long id, String code, String name, TypeTournament type, ModeTournament mode, GameFormula gFormula,
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



	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TournamentSeq")
	@Id
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


	@Enumerated(EnumType.STRING)
	public TypeTournament getType() {
		return type;
	}



	public void setType(TypeTournament type) {
		this.type = type;
	}


	@Enumerated(EnumType.STRING)
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
