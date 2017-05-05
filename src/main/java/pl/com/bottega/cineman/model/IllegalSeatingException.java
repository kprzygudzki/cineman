package pl.com.bottega.cineman.model;

import java.util.Set;

public class IllegalSeatingException extends RuntimeException {

	private final Set<Seat> seats;

	public IllegalSeatingException(String message, Set<Seat> seats) {
		super(message);
		this.seats = seats;
	}

	public Set<Seat> getSeats() {
		return seats;
	}

}
