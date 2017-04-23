package pl.com.bottega.cineman.infrastructure;

import pl.com.bottega.cineman.model.Cinema;
import pl.com.bottega.cineman.model.CinemaRepository;
import pl.com.bottega.cineman.model.ResourceNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class JPACinemaRepository implements CinemaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void put(Cinema cinema) {
		entityManager.persist(cinema);
	}

	@Override
	public Cinema get(Long id) {
		Cinema cinema = entityManager.find(Cinema.class, id);
		if (cinema == null)
			throw new ResourceNotFoundException("cinema", id);
		return cinema;
	}

	@Override
	public boolean existsWithCityAndName(String city, String name) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Cinema> root = criteria.from(Cinema.class);
		criteria.select(builder.count(root));
		criteria.where(builder.equal(root.get("city"), city), builder.equal(root.get("name"), name));
		long count = entityManager.createQuery(criteria).getSingleResult();
		return count != 0L;
	}

}
