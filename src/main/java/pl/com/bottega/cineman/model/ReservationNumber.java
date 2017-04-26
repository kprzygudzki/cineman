package pl.com.bottega.cineman.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Embeddable
public class ReservationNumber implements Serializable {

	private String number;

	public ReservationNumber() {
		this.number = LocalDateTime.now().getMonthValue() + "" +
				LocalDateTime.now().getDayOfMonth() + "-" +
				LocalDateTime.now().getHour() +
				LocalDateTime.now().getMinute() +
				LocalDateTime.now().getSecond();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ReservationNumber)) return false;

		ReservationNumber that = (ReservationNumber) o;

		return number.equals(that.number);
	}

	@Override
	public int hashCode() {
		return number.hashCode();
	}
}
