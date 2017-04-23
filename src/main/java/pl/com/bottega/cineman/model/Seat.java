package pl.com.bottega.cineman.model;

import javax.persistence.Embeddable;

@Embeddable
public class Seat {

	private Integer row;
	private Integer number;

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

}
