package pl.com.bottega.cineman.model;

import pl.com.bottega.cineman.application.InvalidRequestException;
import pl.com.bottega.cineman.model.commands.CalculatePriceCommand;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StandardPriceCalculator implements PriceCalculator {

	private ShowingRepository showingRepository;

	public StandardPriceCalculator(ShowingRepository showingRepository) {
		this.showingRepository = showingRepository;
	}

	@Override
	public CalculationResult calculatePrices(CalculatePriceCommand command) {
		Showing showing = showingRepository.get(command.getShowId());
		Pricing pricing = showing.getPricing();
		Map<String, BigDecimal> prices = pricing.getPrices();
		Set<ReservationItem> tickets = command.getTickets();
		return getCalculationResult(prices, tickets);
	}

	private CalculationResult getCalculationResult(Map<String, BigDecimal> prices, Set<ReservationItem> tickets) {
		Set<CalculationItem> calculationItems = new HashSet<>();
		for (ReservationItem ticket : tickets)
			calculationItems.add(getCalculationItem(prices, ticket));
		return CalculationResult.of(calculationItems);
	}

	private CalculationItem getCalculationItem(Map<String, BigDecimal> prices, ReservationItem ticket) {
		String kind = ticket.getKind();
		if (!prices.containsKey(kind))
			throw new InvalidRequestException(String.format("Pricing for kind %s has not been defined", kind));
		BigDecimal unitPrice = prices.get(kind);
		return CalculationItem.of(ticket, unitPrice);
	}

}
