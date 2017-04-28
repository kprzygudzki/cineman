package pl.com.bottega.cineman.model;

import pl.com.bottega.cineman.model.commands.CalculatePriceCommand;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PriceCalculator {

	private ShowingRepository showingRepository;

	public PriceCalculator(ShowingRepository showingRepository) {
		this.showingRepository = showingRepository;
	}

	public CalculationResult calculatePrices(CalculatePriceCommand cmd) {
		Collection<CalculationItem> calculationItems = new HashSet<>();
		Showing showing = showingRepository.get(cmd.getShowId());
		Pricing pricing = showing.getMovie().getPricing();
		Set<ReservationItem> reservationItems = cmd.getReservationItems();
		BigDecimal unitPrice = (BigDecimal) pricing.getPrices();
		for (ReservationItem reservationItem : reservationItems) {
			CalculationItem items = new CalculationItem(reservationItem, unitPrice);
			calculationItems.add(items);
		}
		return new CalculationResult(calculationItems);
	}

}
