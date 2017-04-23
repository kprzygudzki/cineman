package pl.com.bottega.cineman.infrastructure;

import pl.com.bottega.cineman.model.Showing;
import pl.com.bottega.cineman.model.ShowingRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JPAShowingRepository implements ShowingRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void put(Showing showing) {
		entityManager.persist(showing);
	}

}
