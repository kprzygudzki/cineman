package pl.com.bottega.cineman.application;

import java.util.List;

public interface ReservationCatalog {

	List<ReservationDto> getReservations(ReservationsQuery query);

}
