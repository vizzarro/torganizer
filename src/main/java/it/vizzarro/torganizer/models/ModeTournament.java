/**
 * 
 */
package it.vizzarro.torganizer.models;

import javax.persistence.*;

/**
 * @author Utente
 *DUPLICATO,
 * 	TRIPLICATO,
 * 	QUADRUPLICATO,
 * 	SVEDESE,
 * 	DANESE,
 * 	HOWELL,
 * 	MITCHELL,
 * 	SCRAMBLED
 */
@Entity(name = "MODE_TOURNAMENT")
@SequenceGenerator(name="ModeTournamentSeq",sequenceName ="MODE_TOURNAMENT_SEQ", initialValue=1, allocationSize=1)
public class ModeTournament extends BaseDomain{


	private Game game;

	public ModeTournament() {
		super();
	}

	public ModeTournament(Long id, String code, String name, String description, Game game, Image image) {
		super(id, code, name, description,image);
		this.game = game;
	}

	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="ModeTournamentSeq")
	@Id
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Enumerated(EnumType.STRING)
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "image")
	public Image getImage() {
		return image;
	}

}
