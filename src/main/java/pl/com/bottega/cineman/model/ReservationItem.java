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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ReservationItem that = (ReservationItem) o;

		return (kind != null ? kind.equals(that.kind) :
				that.kind == null) && (count != null ? count.equals(that.count) : that.count == null);
	}

	@Override
	public int hashCode() {
		int result = kind != null ? kind.hashCode() : 0;
		result = 31 * result + (count != null ? count.hashCode() : 0);
		return result;
	}

}
