package pl.com.bottega.cineman.model;

import java.util.Set;

public interface ReservationExporter {

	void addNumber(ReservationNumber number);

	void addStatus(ReservationStatus status);

	void addSeats(Set<Seat> seats);

	void addCustomer(Customer customer);

	default void addItems(Set<ReservationItem> items) {
	}

	void addShowing(Showing showing);

	void addCalculationResult(CalculationResult calculationResult);

}
