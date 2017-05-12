package pl.com.bottega.cineman.application;

import pl.com.bottega.cineman.model.ReservationNumber;

public interface TicketPrinter {

	byte[] printTickets(ReservationNumber reservationNumber);

}
