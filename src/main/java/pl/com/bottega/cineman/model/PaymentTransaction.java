package pl.com.bottega.cineman.model;

import pl.com.bottega.cineman.model.commands.CollectPaymentCommand;

import javax.persistence.*;
import java.time.Instant;

import static pl.com.bottega.cineman.model.PaymentType.CASH;
import static pl.com.bottega.cineman.model.PaymentType.CREDIT_CARD;

@Entity
public class PaymentTransaction {

	@Id
	@GeneratedValue
	private Long id;

	@Enumerated(EnumType.STRING)
	private PaymentType type;

	private Long cashierId;
	private Instant paymentDate;
	private boolean successful;

	private String errorMessage;

	public PaymentTransaction(CollectPaymentCommand command) {
		this.type = CASH;
		this.cashierId = command.getCashierId();
		this.paymentDate = Instant.now();
		this.successful = true;
	}

	public PaymentTransaction(ChargeResult chargeResult) {
		type = CREDIT_CARD;
		paymentDate = Instant.ofEpochSecond(chargeResult.getCreatedAt());
		successful = chargeResult.isPaid();
		errorMessage = chargeResult.getErrorMessage();
	}

	private PaymentTransaction() {
	}

}
