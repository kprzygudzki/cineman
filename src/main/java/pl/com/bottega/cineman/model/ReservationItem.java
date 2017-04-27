package pl.com.bottega.cineman.model;

import javax.persistence.Embeddable;

@Embeddable
public class ReservationItem {

	private String ticketType;
	private Integer quantity;

	public ReservationItem(String ticketType, Integer quantity) {
		this.ticketType = ticketType;
		this.quantity = quantity;
	}

	public String getTicketType() {
		return ticketType;
	}

	public Integer getQuantity() {
		return quantity;
	}
}
