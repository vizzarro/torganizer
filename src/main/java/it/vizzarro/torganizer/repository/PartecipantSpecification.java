/**
 * 
 */
package it.vizzarro.torganizer.repository;

import it.vizzarro.torganizer.models.Partecipant;
import it.vizzarro.torganizer.models.Tournament;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Calendar;

/**
 * Class Specification to filter Tournament JPA Repository.
 * @author Alessandro Vizzarro
 *
 */
public class PartecipantSpecification implements Specification<Partecipant> {

	/**
	 *
	 */
	private static final long serialVersionUID = -5158612835996163646L;
	PartecipantFilter filter;

	/**
	 *
	 */
	public PartecipantSpecification(PartecipantFilter filter) {
		this.filter = filter;
	}
	
	

	@Override
	public Predicate toPredicate(Root<Partecipant> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		
		Predicate conditions = null;
		
		if (filter!=null) {

			if (filter.getNominative()!=null) {
				conditions = conditions!=null ? cb.and(conditions,cb.like(root.get(Partecipant.PROPERTY_NOMINATIVE), "%"+filter.getNominative()+"%")) : cb.like(root.get(Partecipant.PROPERTY_NOMINATIVE), "%"+filter.getNominative()+"%");
			}
		}
				
		return conditions;
	}

}
