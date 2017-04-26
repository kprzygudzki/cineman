package pl.com.bottega.cineman.model.commands;

import pl.com.bottega.cineman.model.Customer;
import pl.com.bottega.cineman.model.ReservationItem;
import pl.com.bottega.cineman.model.Seat;

import java.util.Set;

public class CreateReservationCommand {

	private Long showingId;
	private Set<ReservationItem> tickets;
	private Set<Seat> seats;
	private Customer customer;

	public CreateReservationCommand() {
	}

	public Long getShowingId() {
		return showingId;
	}

	public void setShowingId(Long showingId) {
		this.showingId = showingId;
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
}
