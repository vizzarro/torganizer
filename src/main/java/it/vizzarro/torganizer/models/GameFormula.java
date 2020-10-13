/**
 * 
 */
package it.vizzarro.torganizer.models;

import javax.persistence.*;

/**
 * @author Alessandro Vizzarro
 */
@Entity(name = "GAME_FORMULA")
@SequenceGenerator(name="GameFormulaSeq",sequenceName ="GAME_FORMULA_SEQ", initialValue=1, allocationSize=1)
public class GameFormula extends BaseDomain{

	private Game game;

	public GameFormula() {
		super();
	}

	public GameFormula(Long id, String code, String name, String description, Game game, Image image) {
		super(id, code, name, description,image);
		this.game = game;
	}

	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="GameFormulaSeq")
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

	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "image")
	public Image getImage() {
		return image;
	}

}
