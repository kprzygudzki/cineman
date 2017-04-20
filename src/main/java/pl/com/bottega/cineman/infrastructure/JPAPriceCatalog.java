package pl.com.bottega.cineman.infrastructure;

import pl.com.bottega.cineman.application.PriceCatalog;
import pl.com.bottega.cineman.application.PriceDto;
import pl.com.bottega.cineman.application.PriceDtoBuilder;
import pl.com.bottega.cineman.model.Price;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class JPAPriceCatalog implements PriceCatalog {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<PriceDto> getPrices() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Price> criteriaQuery = builder.createQuery(Price.class);
		criteriaQuery.from(Price.class);
		List<Price> prices = entityManager.createQuery(criteriaQuery).getResultList();
		return createPriceDtos(prices);
	}

	private List<PriceDto> createPriceDtos(List<Price> prices) {
		List<PriceDto> priceDtos = new ArrayList<>();
		PriceDtoBuilder builder = new PriceDtoBuilder();
		for (Price price : prices) {
			price.export(builder);
			PriceDto priceDto = builder.build();
			priceDtos.add(priceDto);
		}
		return priceDtos;
	}

}
