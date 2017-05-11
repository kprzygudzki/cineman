package pl.com.bottega.cineman.model;

import pl.com.bottega.cineman.model.commands.CreateReservationCommand;

import javax.persistence.*;
import java.util.Set;

import static pl.com.bottega.cineman.model.ReservationStatus.PENDING;

@Entity
public class Reservation {

	@EmbeddedId
	private ReservationNumber number;

	@Enumerated(EnumType.STRING)
	private ReservationStatus status;

	@ElementCollection
	private Set<Seat> seats;

	@ElementCollection
	private Set<ReservationItem> items;

	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;

	@ManyToOne
	private Showing showing;

	public Reservation(Showing showing, CreateReservationCommand command) {
		this.showing = showing;
		this.seats = command.getSeats();
		this.items = command.getTickets();
		this.number = ReservationNumber.generate();
		this.status = PENDING;
		this.customer = command.getCustomer();
	}

	public Reservation() {
	}

	Set<Seat> getSeats() {
		return seats;
	}

	public ReservationNumber getNumber() {
		return number;
	}

	public void export(ReservationExporter exporter) {
		exporter.addNumber(number);
		exporter.addStatus(status);
		exporter.addSeats(seats);
		exporter.addItems(items);
		exporter.addShowing(showing);
		exporter.addCustomer(customer);
		exporter.addCalculationResult(getCalculationResult());
	}
}
