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
	private Set<ReservationItem> reservationItems;

	public CalculatePriceCommand() {
	}

	public Long getShowId() {
		return showId;
	}

	public void setShowId(Long showId) {
		this.showId = showId;
	}

	public Set<ReservationItem> getReservationItems() {
		return reservationItems;
	}

	public void setReservationItems(Set<ReservationItem> reservationItems) {
		this.reservationItems = reservationItems;
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
		Set<String> items = new HashSet<>();
		for (ReservationItem reservationItem : reservationItems) {
			items.add(reservationItem.getKind());
			if (items.contains(reservationItem.getKind()))
				errors.add("duplicatedItemType", DUPLICATED_ITEM_TYPE);
		}
	}

	private void invalidItemType(ValidationErrors errors) {
		Set<String> itemsType = new HashSet<>();
		for (ReservationItem reservationItem : reservationItems) {
			itemsType.add(reservationItem.getKind());
			if (!itemsType.contains(reservationItem.getKind()))
				errors.add("invalidItemType", INVALID_ITEM_TYPE);
		}
	}

	private void notExistItem(ValidationErrors errors) {
		if (isNull(reservationItems))
			errors.add("reservationItems", REQUIRED_FIELD);
	}

	private void validateShowId(ValidationErrors errors) {
		if (isNull(showId))
			errors.add("showId", REQUIRED_FIELD);
	}

}
