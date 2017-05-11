package pl.com.bottega.cineman.model;

import pl.com.bottega.cineman.model.commands.Validatable;

import javax.persistence.Embeddable;

@Embeddable
public class Seat implements Validatable {


	private static final String NOT_MORE_THAN = "can not be bigger than %s";

	private Integer row;
	private Integer seat;

	Seat(int row, int number) {
		this.row = row;
		this.seat = number;
	}

	public Seat() {
	}

	public Integer getRow() {
		return row;
	}

	public Integer getSeat() {
		return seat;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public void setSeat(Integer seat) {
		this.seat = seat;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Seat seat = (Seat) o;
		return (row != null ? row.equals(seat.row) : seat.row == null)
				&& (this.seat != null ? this.seat.equals(seat.seat) : seat.seat == null);
	}

	@Override
	public int hashCode() {
		int result = row != null ? row.hashCode() : 0;
		result = 31 * result + (seat != null ? seat.hashCode() : 0);
		return result;
	}

	@Override
	public void validate(ValidationErrors errors) {
		validateRow(errors);
		validateSeat(errors);
	}

	private void validateRow(ValidationErrors errors) {
		if (row == null)
			errors.add("row", REQUIRED_FIELD);
		else {
			if (row.compareTo(1) < 0)
				errors.add("row", POSITIVE_REQUIRED);
			if (row.compareTo(10) > 0)
				errors.add("row", String.format(NOT_MORE_THAN, 10));
		}
	}

	private void validateSeat(ValidationErrors errors) {
		if (seat == null)
			errors.add("seat", REQUIRED_FIELD);
		else {
			if (seat.compareTo(1) < 0)
				errors.add("seat", POSITIVE_REQUIRED);
			if (seat.compareTo(15) > 0)
				errors.add("seat", String.format(NOT_MORE_THAN, 15));
		}
	}

}
