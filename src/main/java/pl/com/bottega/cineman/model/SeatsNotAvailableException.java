package pl.com.bottega.cineman.model;

import java.util.Set;

public class SeatsNotAvailableException extends RuntimeException {

	private final Set<Seat> unavailableSeats;

	public SeatsNotAvailableException(Set<Seat> unavailableSeats) {
		this.unavailableSeats = unavailableSeats;
	}

	public Set<Seat> getUnavailableSeats() {
		return unavailableSeats;
	}

}
