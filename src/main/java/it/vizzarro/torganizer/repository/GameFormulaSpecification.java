/**
 * 
 */
package it.vizzarro.torganizer.repository;

import it.vizzarro.torganizer.models.GameFormula;
import it.vizzarro.torganizer.models.Tournament;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Class Specification to filter Tournament JPA Repository.
 * @author Alessandro Vizzarro
 *
 */
public class GameFormulaSpecification implements Specification<GameFormula> {

	/**
	 *
	 */
	private static final long serialVersionUID = -5158612835996163646L;
	private GameFormulaFilter filter;

	/**
	 *
	 */
	public GameFormulaSpecification(GameFormulaFilter filter) {
		this.filter = filter;
	}
	
	

	@Override
	public Predicate toPredicate(Root<GameFormula> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		
		Predicate conditions = null;
		
		if (filter!=null) {
			
			if (filter.getGame()!=null) {
				conditions = conditions!=null ? cb.and(conditions,cb.equal(root.get("game"), filter.getGame())) : cb.equal(root.get("code"), filter.getGame());
			}


		}
				
		return conditions;
	}

}
