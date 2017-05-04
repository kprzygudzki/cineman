package pl.com.bottega.cineman.model.commands;

import pl.com.bottega.cineman.model.Customer;
import pl.com.bottega.cineman.model.ReservationItem;
import pl.com.bottega.cineman.model.Seat;

import java.util.Set;

public class CreateReservationCommand implements Validatable {

	private Long showId;
	private Set<ReservationItem> tickets;
	private Set<Seat> seats;
	private Customer customer;

	public CreateReservationCommand() {
	}

	public Long getShowId() {
		return showId;
	}

	public void setShowId(Long showId) {
		this.showId = showId;
	}

	public Set<ReservationItem> getTickets() {
		return tickets;
	}

	public void setTickets(Set<ReservationItem> tickets) {
		this.tickets = tickets;
	}

	public Set<Seat> getSeats() {
		return seats;
	}

	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public void validate(ValidationErrors errors) {
		if (tickets.size() == 0)
			errors.add("tickets", "required at least one item");
	}
}
