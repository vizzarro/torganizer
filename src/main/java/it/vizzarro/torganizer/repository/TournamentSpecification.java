/**
 * 
 */
package it.vizzarro.torganizer.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import it.vizzarro.torganizer.models.Tournament;

import java.util.Calendar;
import java.util.Date;

/**
 * Class Specification to filter Tournament JPA Repository.
 * @author Alessandro Vizzarro
 *
 */
public class TournamentSpecification implements Specification<Tournament> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5158612835996163646L;
	TournamentFilter filter;
	
	/**
	 * 
	 */
	public TournamentSpecification(TournamentFilter filter) {
		this.filter = filter;
	}
	
	

	@Override
	public Predicate toPredicate(Root<Tournament> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		
		Predicate conditions = null;
		
		if (filter!=null) {
			
			if (filter.getCode()!=null) {
				conditions = conditions!=null ? cb.and(conditions,cb.like(root.get(Tournament.PROPERTY_CODE), "%"+filter.getCode()+"%")) : cb.like(root.get(Tournament.PROPERTY_CODE), "%"+filter.getCode()+"%") ;
			}
			if (filter.getName()!=null) {
				conditions = conditions!=null ? cb.and(conditions,cb.like(root.get(Tournament.PROPERTY_NAME), "%"+filter.getName()+"%")) : cb.like(root.get(Tournament.PROPERTY_NAME), "%"+filter.getName()+"%");
			}
			if (filter.getDateCreation()!=null) {
				Calendar c = Calendar.getInstance();
				c.setTimeInMillis(filter.getDateCreation());
				conditions = conditions!=null ? cb.and(conditions,cb.equal(root.get(Tournament.PROPERTY_DATA_CREATION), c.getTime())) : cb.equal(root.get(Tournament.PROPERTY_DATA_CREATION), c.getTime());
			}
		}
				
		return conditions;
	}

}
