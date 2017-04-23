package pl.com.bottega.cineman.model;

public class Seat {

	private Integer row;
	private Integer number;

	public Seat(int row, int number) {
		this.number = row;
		this.row = number;
	}

	public Integer getRow() {
		return row;
	}

	public Integer getNumber() {
		return number;
	}

}
