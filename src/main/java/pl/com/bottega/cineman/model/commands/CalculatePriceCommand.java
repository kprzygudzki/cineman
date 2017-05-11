package pl.com.bottega.cineman.model.commands;

import pl.com.bottega.cineman.model.ReservationItem;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.isNull;

public class CalculatePriceCommand implements Validatable {

	private Long showId;
	private Set<ReservationItem> tickets;

	public CalculatePriceCommand() {
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

	@Override
	public void validate(ValidationErrors errors) {
		validateShowId(errors);
		validateTickets(errors);
	}

	private void validateShowId(ValidationErrors errors) {
		if (isNull(showId))
			errors.add("showId", REQUIRED_FIELD);
	}

	private void validateTickets(ValidationErrors errors) {
		if (isNull(tickets) || tickets.isEmpty()) {
			errors.add("tickets", REQUIRED_FIELD);
			return;
		}
		if (tickets.remove(null))
			errors.add("tickets", NON_NULL_ELEMENT);
		for (ReservationItem ticket : tickets)
			ticket.validate(errors);
		validateUniqueKinds(errors);
	}

	private void validateUniqueKinds(ValidationErrors errors) {
		Set<String> kinds = new HashSet<>();
		for (ReservationItem ticket : tickets) {
			if (!kinds.add(ticket.getKind()))
				errors.add("tickets", UNIQUE_TICKET_KINDS);
		}
	}

}
