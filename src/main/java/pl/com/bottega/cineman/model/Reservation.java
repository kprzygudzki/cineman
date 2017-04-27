package pl.com.bottega.cineman.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Reservation {

	@Id
	@GeneratedValue
	private Long id;

	@ElementCollection
	private Set<Seat> seats;

	@ElementCollection
	private Set<ReservationItem> items;

	@Embedded
	@AttributeOverride(name = "number", column = @Column(name = "reservation_number"))
	private ReservationNumber reservationNumber;

	@Enumerated(EnumType.STRING)
	private ReservationStatus status;

	@OneToOne
	private Customer customer;

	public Reservation(Set<Seat> seats, Set<ReservationItem> reservationItems, Customer customer) {
		this.seats = seats;
		this.items = reservationItems;
		this.reservationNumber = new ReservationNumber();
		this.status = ReservationStatus.PENDING;
	}

	Set<Seat> getSeats() {
		return seats;
	}

	public ReservationNumber getReservationNumber() {
		return reservationNumber;
	}
}
