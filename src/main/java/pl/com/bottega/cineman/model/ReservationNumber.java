package pl.com.bottega.cineman.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class ReservationNumber implements Serializable {

	private String reservationNumber;

	private ReservationNumber(String reservationNumber) {
		this.reservationNumber = reservationNumber;
	}

	public static ReservationNumber generate() {
		return new ReservationNumber(UUID.randomUUID().toString());
	}

	private ReservationNumber() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ReservationNumber)) return false;
		ReservationNumber that = (ReservationNumber) o;
		return reservationNumber.equals(that.reservationNumber);
	}

	@Override
	public int hashCode() {
		return reservationNumber.hashCode();
	}

	public String getReservationNumber() {
		return reservationNumber;
	}

}
