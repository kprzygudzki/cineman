package pl.com.bottega.cineman.model;

import javax.persistence.Embeddable;

@Embeddable
public class ReservationItem {

	private String kind;
	private Integer quantity;
	//TODO should be "count"

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
