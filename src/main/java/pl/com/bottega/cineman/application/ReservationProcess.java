package pl.com.bottega.cineman.application;

import pl.com.bottega.cineman.model.ReservationNumber;
import pl.com.bottega.cineman.model.commands.CreateReservationCommand;

public interface ReservationProcess {

	ViewingRoomDto getSeats(Long showId);

	ReservationNumber create(CreateReservationCommand command);

}
