package pl.com.bottega.cineman.model;

import java.util.Set;

public class SeatingNotAvailableException extends RuntimeException {

	private final Set<Seat> seats;

	public SeatingNotAvailableException(Set<Seat> seats) {
		this.seats = seats;
	}

	public Set<Seat> getSeats() {
		return seats;
	}

}
