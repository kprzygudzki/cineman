package pl.com.bottega.cineman.model.commands;

import pl.com.bottega.cineman.model.CreditCard;
import pl.com.bottega.cineman.model.PaymentType;
import pl.com.bottega.cineman.model.ReservationNumber;

import static java.util.Objects.isNull;

public class CollectPaymentCommand implements Validatable {

	private static final String REQUIRED_FIELD = "missing required field";

	private PaymentType type;
	private Long cashierId;
	private CreditCard creditCard;
	private ReservationNumber reservationNumber;

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}

	public Long getCashierId() {
		return cashierId;
	}

	public void setCashierId(Long cashierId) {
		this.cashierId = cashierId;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public ReservationNumber getReservationNumber() {
		return reservationNumber;
	}

	public void setReservationNumber(ReservationNumber reservationNumber) {
		this.reservationNumber = reservationNumber;
	}

	@Override
	public void trimAndValidate(ValidationErrors errors) {
		validatePaymentType(errors);
		validateCashierId(errors);
	}

	private void validateCashierId(ValidationErrors errors) {
		if (isNull(cashierId))
			errors.add("cashier id", REQUIRED_FIELD);
	}

	private void validatePaymentType(ValidationErrors errors) {
		if (isNull(type))
			errors.add("payment type", REQUIRED_FIELD);
	}
}
