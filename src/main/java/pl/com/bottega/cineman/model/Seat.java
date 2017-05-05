package pl.com.bottega.cineman.model;

import javax.persistence.Embeddable;

@Embeddable
public class Seat {

	private Integer row;
	private Integer number;
	//TODO should be "seat"

	Seat(int row, int number) {
		this.row = row;
		this.number = number;
	}

	public Seat() {
	}

	Integer getRow() {
		return row;
	}

	Integer getNumber() {
		return number;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Seat seat = (Seat) o;
		return (row != null ? row.equals(seat.row) : seat.row == null) && (number != null ? number.equals(seat.number) : seat.number == null);
	}

	@Override
	public int hashCode() {
		int result = row != null ? row.hashCode() : 0;
		result = 31 * result + (number != null ? number.hashCode() : 0);
		return result;
	}

}
