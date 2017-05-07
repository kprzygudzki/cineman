package pl.com.bottega.cineman.model.commands;

import pl.com.bottega.cineman.model.ReservationItem;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.isNull;

public class CalculatePriceCommand implements Validatable {

	private static final String REQUIRED_FIELD = "missing required field";
	private static final String DUPLICATED_ITEM_TYPE = "item type can not be duplicated";
	public static final String NO_NULL_ELEMENTS = "can not contain null elements";

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
		validateReservationItem(errors);
	}

	private void validateReservationItem(ValidationErrors errors) {
		notExistItem(errors);
		validateNoTickets(errors);
		duplicatedItemType(errors);
	}

	private void duplicatedItemType(ValidationErrors errors) {
		Set<String> kinds = new HashSet<>();
		for (ReservationItem ticket : tickets) {
			if (!kinds.add(ticket.getKind()))
				errors.add("duplicatedItemType", DUPLICATED_ITEM_TYPE);
		}
	}

	private void notExistItem(ValidationErrors errors) {
		if (isNull(tickets))
			errors.add("tickets", REQUIRED_FIELD);
	}

	private void validateShowId(ValidationErrors errors) {
		if (isNull(showId))
			errors.add("showId", REQUIRED_FIELD);
	}

	private void validateNoTickets(ValidationErrors errors) {
		if (tickets.isEmpty())
			errors.add("tickets", REQUIRED_FIELD);
		if (tickets.contains(null))
			errors.add("tickets", NO_NULL_ELEMENTS);
		tickets.remove(null);
		for (ReservationItem ticket : tickets) {
			if (isNull(ticket.getKind()) || ticket.getKind().trim().isEmpty())
				errors.add("ticket kind", REQUIRED_FIELD);
			if (isNull(ticket.getCount()))
				errors.add("ticket count", REQUIRED_FIELD);
		}
	}

}
