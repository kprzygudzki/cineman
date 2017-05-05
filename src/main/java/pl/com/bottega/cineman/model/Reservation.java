package pl.com.bottega.cineman.model;

import pl.com.bottega.cineman.model.commands.CreateReservationCommand;

import javax.persistence.*;
import java.util.Set;

import static pl.com.bottega.cineman.model.ReservationStatus.PENDING;

@Entity
public class Reservation {

	@Id
	@GeneratedValue
	private Long id; //TODO remove and replace with ReservationNumber as an embedded id

	@Enumerated(EnumType.STRING)
	private ReservationStatus status;

	@ElementCollection
	private Set<Seat> seats;

	@ElementCollection
	private Set<ReservationItem> items;

	@Embedded
	@AttributeOverride(name = "number", column = @Column(name = "reservation_number"))
	private ReservationNumber reservationNumber;

	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;

	public Reservation(CreateReservationCommand command) {
		seats = command.getSeats();
		items = command.getTickets();
		reservationNumber = ReservationNumber.generate();
		status = PENDING;
		customer = command.getCustomer();
	}

	public Reservation() {
	}

	Set<Seat> getSeats() {
		return seats;
	}

	public ReservationNumber getReservationNumber() {
		return reservationNumber;
	}

}
