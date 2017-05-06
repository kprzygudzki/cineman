package pl.com.bottega.cineman.model;

import pl.com.bottega.cineman.application.InvalidRequestException;
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

	public CalculationResult calculatePrices(CalculatePriceCommand command) {
		Collection<CalculationItem> calculationItems = new HashSet<>();
		Showing showing = showingRepository.get(command.getShowId());
		Set<ReservationItem> tickets = command.getTickets();
		Map<String, BigDecimal> pricing = showing.getMovie().getPricing().getPrices();
		for (ReservationItem ticket : tickets) {
			String kind = ticket.getKind();
			if (!pricing.containsKey(kind))
				throw new InvalidRequestException(String.format("%s is not a valid kind of ticket", kind));
			BigDecimal unitPrice = pricing.get(kind);
			CalculationItem items = new CalculationItem(ticket, unitPrice);
			calculationItems.add(items);
		}
		return new CalculationResult(calculationItems);
	}

}
