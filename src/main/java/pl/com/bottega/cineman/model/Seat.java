package pl.com.bottega.cineman.model;

import javax.persistence.Embeddable;

@Embeddable
public class Seat {

	private Integer row;
	private Integer number;

	public Seat() {

	}

	Seat(int row, int number) {
		this.number = row;
		this.row = number;
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
}
