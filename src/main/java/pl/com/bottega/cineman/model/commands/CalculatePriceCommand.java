package pl.com.bottega.cineman.model.commands;

import pl.com.bottega.cineman.model.ReservationItem;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.isNull;

public class CalculatePriceCommand implements Validatable {

	private static final String REQUIRED_FIELD = "missing required field";
	private static final String DUPLICATED_ITEM_TYPE = "item type can not be duplicated";
	private static final String INVALID_ITEM_TYPE = "invalid item type";

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
		invalidItemType(errors);
		duplicatedItemType(errors);
	}

	private void duplicatedItemType(ValidationErrors errors) {
		for (ReservationItem ticket : tickets) {
			for (ReservationItem ticket2 : tickets) {
				if (ticket.getKind() != ticket2.getKind() && ticket.getKind().equals(ticket2.getKind()))
					errors.add("duplicatedItemType", DUPLICATED_ITEM_TYPE);
			}
		}
	}

	private void invalidItemType(ValidationErrors errors) {
		Set<String> itemsType = new HashSet<>();
		for (ReservationItem reservationItem : tickets) {
			itemsType.add(reservationItem.getKind());
			if (!itemsType.contains(reservationItem.getKind()))
				errors.add("invalidItemType", INVALID_ITEM_TYPE);
		}
	}

	private void notExistItem(ValidationErrors errors) {
		if (isNull(tickets))
			errors.add("reservationItems", REQUIRED_FIELD);
	}

	private void validateShowId(ValidationErrors errors) {
		if (isNull(showId))
			errors.add("showId", REQUIRED_FIELD);
	}

}
