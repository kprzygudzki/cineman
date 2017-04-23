package pl.com.bottega.cineman.infrastructure;

import pl.com.bottega.cineman.model.ResourceNotFoundException;
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

	@Override
	public Showing get(Long showingId) {
		Showing showing = entityManager.find(Showing.class, showingId);
		if (showing == null)
			throw new ResourceNotFoundException("showing", showingId);
		return showing;
	}

}
