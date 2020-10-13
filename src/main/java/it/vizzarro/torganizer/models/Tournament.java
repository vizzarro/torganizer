/**
 * 
 */
package it.vizzarro.torganizer.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * @author Alessandro Vizzarro
 *
 */
@Entity(name = "TOURNAMENT")
@SequenceGenerator(name="TournamentSeq",sequenceName ="TOURNAMENT_SEQ", initialValue=1, allocationSize=1)
public class Tournament  {

	public static final String PROPERTY_ID = "id";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_MODE = "mode";
	public static final String PROPERTY_GAME_FORMULA = "gFormula";
	public static final String PROPERTY_REFEREE = "referee";
	public static final String PROPERTY_SITE = "site";
	public static final String PROPERTY_TIMES = "times";
	public static final String PROPERTY_BOARDS = "boards";
	public static final String PROPERTY_BONUS = "bonus";
	public static final String PROPERTY_DATA_CREATION = "dataCreation";
	public static final String PROPERTY_GAME = "game";
	public static final String PROPERTY_ROUNDS = "rounds";

	private Long id;
	private String code;
	private String name;
	private TypeTournament type;
	private ModeTournament mode;
	private GameFormula gameFormula;
	private String referee;
	private String site;
	private Integer times;
	private Integer boards;
	private Boolean bonus;
	private Date dataCreation;
	private Game game;
	private Set<RoundMatch> rounds;

	
	public Tournament() {
		super();
	}



	public Tournament(Long id, String code, String name, TypeTournament type, ModeTournament mode, GameFormula gFormula,
			String referee, String site, Integer times, Integer boards, Boolean bonus, Date dataCreation,Game game) {
		this();
		this.id = id;
		this.code = code;
		this.name = name;
		this.type = type;
		this.mode = mode;
		this.gameFormula = gFormula;
		this.referee = referee;
		this.site = site;
		this.times = times;
		this.boards = boards;
		this.bonus = bonus;
		this.dataCreation = dataCreation;
		this.game = game;
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


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tournament_modality")
	public ModeTournament getMode() {
		return mode;
	}



	public void setMode(ModeTournament mode) {
		this.mode = mode;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "game_formula")
	public GameFormula getGameFormula() {
		return gameFormula;
	}



	public void setGameFormula(GameFormula gameFormula) {
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

	@Enumerated(EnumType.STRING)
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@OneToMany(mappedBy = "tournament")
	public Set<RoundMatch> getRounds() {
		return rounds;
	}

	public void setRounds(Set<RoundMatch> rounds) {
		this.rounds = rounds;
	}
}
