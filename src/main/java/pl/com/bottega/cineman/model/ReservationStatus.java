package pl.com.bottega.cineman.model;


import pl.com.bottega.cineman.application.InvalidRequestException;

public enum ReservationStatus {

	PENDING,
	PAID {
		public void ensureCanAcceptPayment() {
			throw new InvalidRequestException("Reservation has already been payed and it can not accept payment");
		}
	}, PAYMENT_FAILED,
	CANCELLED {
		public void ensureCanAcceptPayment() {
			throw new InvalidRequestException("Reservation has been cancelled and it can not accept payment");
		}
	};

	public void ensureCanAcceptPayment() {
	}

}
