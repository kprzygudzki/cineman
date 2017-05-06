package pl.com.bottega.cineman.model.commands;

import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.xml.internal.security.signature.ObjectContainer;
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
		duplicatedItemType(errors);
		validateNoTickets(errors);
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
			errors.add("reservationItems", REQUIRED_FIELD);
	}

	private void validateShowId(ValidationErrors errors) {
		if (isNull(showId))
			errors.add("showId", REQUIRED_FIELD);
	}

	private void validateNoTickets(ValidationErrors errors) {
		if (tickets.isEmpty())
			errors.add("noTickets", REQUIRED_FIELD);
		tickets.remove(null);
		for (ReservationItem ticket : tickets) {
			if (isNull(ticket.getKind()) || ticket.getKind().trim().isEmpty())
				errors.add("noKind", REQUIRED_FIELD);
			if (isNull(ticket.getCount()))
				errors.add("noCount", REQUIRED_FIELD);
		}
	}

}
