package pl.com.bottega.cineman.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ReservationItem {

	private String kind;
	@Column(name = "quantity")
	private Integer count;

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
