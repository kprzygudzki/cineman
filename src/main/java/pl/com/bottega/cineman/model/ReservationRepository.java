package pl.com.bottega.cineman.model;

public interface ReservationRepository {

	Reservation get(ReservationNumber reservationNumber);

}
