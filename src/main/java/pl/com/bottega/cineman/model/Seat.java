package pl.com.bottega.cineman.model;

import javax.persistence.Embeddable;

@Embeddable
public class Seat {

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
		return (row != null ? row.equals(seat.row) : seat.row == null) && (this.seat != null ? this.seat.equals(seat.seat) : seat.seat == null);
	}

	@Override
	public int hashCode() {
		int result = row != null ? row.hashCode() : 0;
		result = 31 * result + (seat != null ? seat.hashCode() : 0);
		return result;
	}

}
