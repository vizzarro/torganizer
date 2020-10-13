/**
 * 
 */
package it.vizzarro.torganizer.repository;

import it.vizzarro.torganizer.models.ModeTournament;
import it.vizzarro.torganizer.models.RoundMatch;
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
public class RoundMatchSpecification implements Specification<RoundMatch> {

	/**
	 *
	 */
	private static final long serialVersionUID = -5158612835996163646L;
	private RoundMatchFilter filter;

	/**
	 *
	 */
	public RoundMatchSpecification(RoundMatchFilter filter) {
		this.filter = filter;
	}
	
	

	@Override
	public Predicate toPredicate(Root<RoundMatch> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

		Predicate conditions = null;

		if (filter != null) {


			if (filter.getTournamentId() != null) {
				conditions = conditions != null ? cb.and(conditions, cb.equal(root.get("code"), filter.getTournamentId())) : cb.equal(root.get("code"), filter.getTournamentId());

			}
		}

		return conditions;

	}
}
