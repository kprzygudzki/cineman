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

	public static ReservationNumber from(String number) {
		return new ReservationNumber(number);
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

	@Override
	public String toString() {
		return reservationNumber;
	}

	public String getReservationNumber() {
		return reservationNumber;
	}

}
