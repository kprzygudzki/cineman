package pl.com.bottega.cineman.infrastructure;

import pl.com.bottega.cineman.model.Price;
import pl.com.bottega.cineman.model.PriceRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JPAPriceRepository implements PriceRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void put(Price price) {
		entityManager.persist(price);
	}
}
