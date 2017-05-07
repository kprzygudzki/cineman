package pl.com.bottega.cineman.model.commands;

import pl.com.bottega.cineman.model.Customer;
import pl.com.bottega.cineman.model.ReservationItem;
import pl.com.bottega.cineman.model.Seat;

import java.util.HashSet;
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
		validateShowId(errors);
		validateSeats(errors);
		validateTickets(errors);
		validateCustomer(errors);
	}

	private void validateCustomer(ValidationErrors errors) {
		if (customer == null)
			errors.add("customer", REQUIRED_FIELD);
		else
			customer.validate(errors);
	}

	private void validateShowId(ValidationErrors errors) {
		if (showId == null)
			errors.add("showId", REQUIRED_FIELD);
	}

	private void validateTickets(ValidationErrors errors) {
		if (tickets == null || tickets.isEmpty()) {
			errors.add("tickets", REQUIRED_FIELD);
			return;
		}
		if (tickets.remove(null))
			errors.add("tickets", NON_NULL_ELEMENT);
		for (ReservationItem ticket : tickets)
			ticket.validate(errors);
		validateUniqueKinds(errors);
		validateTicketCount(errors);
	}

	private void validateUniqueKinds(ValidationErrors errors) {
		Set<String> kinds = new HashSet<>();
		for (ReservationItem ticket : tickets)
			if (!kinds.add(ticket.getKind()))
				errors.add("tickets", UNIQUE_TICKET_KINDS);
	}

	private void validateTicketCount(ValidationErrors errors) {
		int ticketCount = 0;
		for (ReservationItem ticket : tickets) {
			if (ticket.getCount() != null)
				ticketCount += ticket.getCount();
		}
		if (seats != null && ticketCount != seats.size())
			errors.add("ticket count and seat count", EQUAL_REQUIRED);
	}

	private void validateSeats(ValidationErrors errors) {
		if (seats == null || seats.isEmpty()) {
			errors.add("seats", REQUIRED_FIELD);
			return;
		}
		if (seats.remove(null))
			errors.add("seats", NON_NULL_ELEMENT);
		for (Seat seat : seats) {
			seat.validate(errors);
		}
	}

}
