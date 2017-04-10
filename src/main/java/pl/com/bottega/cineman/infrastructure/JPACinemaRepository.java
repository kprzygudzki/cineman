package pl.com.bottega.cineman.infrastructure;

import pl.com.bottega.cineman.model.Cinema;
import pl.com.bottega.cineman.model.CinemaNotFoundException;
import pl.com.bottega.cineman.model.CinemaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


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
		if(cinema == null)
			throw new CinemaNotFoundException(id);
		return cinema;
	}
}