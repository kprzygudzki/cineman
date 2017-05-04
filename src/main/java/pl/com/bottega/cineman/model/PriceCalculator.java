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
		Set<ReservationItem> tickets = cmd.getTickets();
		Map<String, BigDecimal> pricing = showing.getMovie().getPricing().getPrices();
		for (ReservationItem ticket : tickets) {
			String kind = ticket.getKind();
			BigDecimal unitPrice = pricing.get(kind);
			CalculationItem items = new CalculationItem(ticket, unitPrice);
			calculationItems.add(items);
		}
		return new CalculationResult(calculationItems);
	}

}