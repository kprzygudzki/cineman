package pl.com.bottega.cineman.model;

public class PaymentFailureException extends RuntimeException {

	public PaymentFailureException(String errorMessage) {
		super(errorMessage);
	}

}
