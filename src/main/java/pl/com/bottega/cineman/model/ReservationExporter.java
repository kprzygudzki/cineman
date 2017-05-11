package pl.com.bottega.cineman.model;

import java.util.Set;

public interface ReservationExporter {

	void addNumber(ReservationNumber number);

	void addStatus(ReservationStatus status);

	void addSeats(Set<Seat> seats);

	void addItemsAndShowing(Set<ReservationItem> items, Showing showing);

	void addCustomer(Customer customer);

}
