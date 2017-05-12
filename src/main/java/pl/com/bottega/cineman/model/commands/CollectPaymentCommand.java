package pl.com.bottega.cineman.model.commands;

import pl.com.bottega.cineman.model.CreditCard;
import pl.com.bottega.cineman.model.PaymentType;
import pl.com.bottega.cineman.model.ReservationNumber;

import static java.util.Objects.isNull;
import static pl.com.bottega.cineman.model.PaymentType.CASH;
import static pl.com.bottega.cineman.model.PaymentType.CREDIT_CARD;

public class CollectPaymentCommand implements Validatable {

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
	public void validate(ValidationErrors errors) {
		validatePaymentType(errors);
		if (type == CASH)
			validateCashierId(errors);
		else if (type == CREDIT_CARD)
			validateCreditCard(errors);
	}

	private void validateCreditCard(ValidationErrors errors) {
		if (creditCard != null)
			creditCard.validate(errors);
		else
			errors.add("creditCard", REQUIRED_FIELD);
	}

	private void validateCashierId(ValidationErrors errors) {
		if (cashierId == null)
			errors.add("cashierId", REQUIRED_FIELD);
	}

	private void validatePaymentType(ValidationErrors errors) {
		if (isNull(type))
			errors.add("type", REQUIRED_FIELD);
	}

}
