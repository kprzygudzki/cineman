package pl.com.bottega.cineman.model;

import pl.com.bottega.cineman.model.commands.DefineMoviePricesCommand;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Pricing {

	@Id
	@GeneratedValue
	private Long id;

	@ElementCollection
	private Map<String, BigDecimal> prices = new HashMap<>();

	public Pricing(DefineMoviePricesCommand command){
		prices.putAll(command.getPrices());
	}

	Pricing() {
	}

	public void update(DefineMoviePricesCommand command) {
		prices.clear();
		prices.putAll(command.getPrices());
	}

	public Map<String, BigDecimal> getPrices() {
		return prices;
	}
}
