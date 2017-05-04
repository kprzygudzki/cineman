package pl.com.bottega.cineman.application;

import pl.com.bottega.cineman.model.CalculationResult;
import pl.com.bottega.cineman.model.ReservationNumber;
import pl.com.bottega.cineman.model.commands.CalculatePriceCommand;
import pl.com.bottega.cineman.model.commands.CreateReservationCommand;

public interface ReservationProcess {

	ViewingRoomDto getSeats(Long showId);

	CalculationResult calculatePrices(CalculatePriceCommand cmd);

	ReservationNumber create(CreateReservationCommand command);

}
