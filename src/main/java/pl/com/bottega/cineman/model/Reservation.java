package pl.com.bottega.cineman.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Set;

@Entity
public class Reservation {

	@Id
	@GeneratedValue
	private Long id;

	@ElementCollection
	private Set<Seat> seats;

	Set<Seat> getSeats() {
		return seats;
	}

}
