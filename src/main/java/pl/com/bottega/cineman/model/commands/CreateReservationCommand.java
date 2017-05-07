package pl.com.bottega.cineman.model.commands;

import pl.com.bottega.cineman.model.Customer;
import pl.com.bottega.cineman.model.ReservationItem;
import pl.com.bottega.cineman.model.Seat;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.isNull;

public class CreateReservationCommand implements Validatable {

	private static final String REQUIRED_FIELD = "is a required field and can not be empty";
	private static final String AT_LEAST_ONE = "must at least be 1";
	private static final String NOT_MORE_THAN = "must be no bigger than %s";
	private static final String UNIQUE_TICKET_KINDS = "must contain unique ticket kinds";
	private static final String NOT_EQUAL_NUMBER = "must be equal to seats number";
	private static final String NOT_NULL_ELEMENT = "cannot contain empty elements";

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
	public void trimAndValidate(ValidationErrors errors) {
		if (showId == null)
			errors.add("showId", REQUIRED_FIELD);
		if (seats == null)
			errors.add("seats", REQUIRED_FIELD);
		else
			validateSeats(errors);
		if (tickets == null)
			errors.add("tickets", REQUIRED_FIELD);
		else
			validateTickets(errors);
		if (customer == null)
			errors.add("customer", REQUIRED_FIELD);
		else
			customer.trimAndValidate(errors);
	}

	private void validateSeats(ValidationErrors errors) {
		seats.remove(null);
		if (seats.isEmpty())
			errors.add("seats", REQUIRED_FIELD);
		for (Seat seat : seats) {
			validateRow(errors, seat);
			validateSeat(errors, seat);
		}
	}

	private void validateRow(ValidationErrors errors, Seat seat) {
		Integer row = seat.getRow();
		if (row == null)
			errors.add("seat row", REQUIRED_FIELD);
		else {
			if (row.compareTo(1) < 0)
				errors.add("seat row", AT_LEAST_ONE);
			if (row.compareTo(10) > 0)
				errors.add("seat row", String.format(NOT_MORE_THAN, 10));
		}
	}

	private void validateSeat(ValidationErrors errors, Seat seat) {
		Integer row = seat.getSeat();
		if (row == null)
			errors.add("seat number", REQUIRED_FIELD);
		else {
			if (row.compareTo(1) < 0)
				errors.add("seat number", AT_LEAST_ONE);
			if (row.compareTo(15) > 0)
				errors.add("seat number", String.format(NOT_MORE_THAN, 15));
		}
	}

	private void validateTickets(ValidationErrors errors) {
		if (tickets == null || tickets.isEmpty())
			errors.add("tickets", REQUIRED_FIELD);
		if (tickets.contains(null))
			errors.add("tickets", NOT_NULL_ELEMENT);
		tickets.remove(null);
		for (ReservationItem ticket : tickets) {
			if (isNull(ticket.getCount()))
				errors.add("ticket count", REQUIRED_FIELD);
			if (isNull(ticket.getKind()) || ticket.getKind().trim().isEmpty())
				errors.add("ticket kind", REQUIRED_FIELD);
		}
		for (ReservationItem ticket : tickets) {
			ensureTicketKindPresent(errors, ticket);
			ensureTicketCountPresent(errors, ticket);
			ensureTicketCountBiggerThenZero(errors, ticket);
		}
		ensureTicketCountEqualsSeats(errors);
		ensureUniqueTicketKinds(errors);
	}

	private void ensureTicketCountEqualsSeats(ValidationErrors errors) {
		Integer ticketCount = 0;
		for (ReservationItem ticket : tickets) {
			if (ticket.getCount() != null)
				ticketCount += ticket.getCount();
		}
		if (seats != null && ticketCount != seats.size())
			errors.add("ticket count", NOT_EQUAL_NUMBER);
	}

	private void ensureTicketCountBiggerThenZero(ValidationErrors errors, ReservationItem ticket) {
		if (ticket.getCount() != null && ticket.getCount().compareTo(1) < 0)
			errors.add("ticket count", AT_LEAST_ONE);
	}

	private void ensureTicketCountPresent(ValidationErrors errors, ReservationItem ticket) {
		if (ticket.getCount() == null)
			errors.add("ticket count", REQUIRED_FIELD);
	}

	private void ensureTicketKindPresent(ValidationErrors errors, ReservationItem ticket) {
		if (ticket.getKind() == null || ticket.getKind().trim().isEmpty())
			errors.add("ticket kind", REQUIRED_FIELD);
	}

	private void ensureUniqueTicketKinds(ValidationErrors errors) {
		Set<String> kinds = new HashSet<>();
		for (ReservationItem ticket : tickets)
			if (!kinds.add(ticket.getKind()))
				errors.add("tickets", UNIQUE_TICKET_KINDS);
	}

}
