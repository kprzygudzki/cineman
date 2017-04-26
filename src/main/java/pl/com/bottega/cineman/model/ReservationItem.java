package pl.com.bottega.cineman.model;

import javax.persistence.Embeddable;

@Embeddable
public class ReservationItem {

	private String ticketType;
	private Integer count;

	public ReservationItem(String ticketType, Integer count) {
		this.ticketType = ticketType;
		this.count = count;
	}

	public String getTicketType() {
		return ticketType;
	}

	public Integer getCount() {
		return count;
	}
}
