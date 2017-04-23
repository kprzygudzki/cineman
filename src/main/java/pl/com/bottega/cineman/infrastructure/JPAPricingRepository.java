package pl.com.bottega.cineman.infrastructure;import pl.com.bottega.cineman.model.Pricing;import pl.com.bottega.cineman.model.PricingRepository;import pl.com.bottega.cineman.model.ResourceNotFoundException;import javax.persistence.EntityManager;import javax.persistence.PersistenceContext;public class JPAPricingRepository implements PricingRepository {	@PersistenceContext	private EntityManager entityManager;	@Override	public void put(Pricing pricing) {		entityManager.persist(pricing);	}	@Override	public Pricing get(Long id) {		Pricing pricing = entityManager.find(Pricing.class, id);		if(pricing == null)			throw new ResourceNotFoundException("pricing", id);		return pricing;	}}