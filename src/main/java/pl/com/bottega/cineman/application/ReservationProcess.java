package pl.com.bottega.cineman.application;

import pl.com.bottega.cineman.model.CalculationResult;
import pl.com.bottega.cineman.model.commands.CalculatePriceCommand;

public interface ReservationProcess {

	ViewingRoomDto getSeats(Long showId);

	CalculationResult calculatePrices(CalculatePriceCommand cmd);

}
