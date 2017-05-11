package pl.com.bottega.cineman.model;

import pl.com.bottega.cineman.model.commands.CalculatePriceCommand;

public interface PriceCalculator {

	CalculationResult calculatePrices(CalculatePriceCommand command);

}
