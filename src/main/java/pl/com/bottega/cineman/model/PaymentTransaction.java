package pl.com.bottega.cineman.model;

import pl.com.bottega.cineman.model.commands.CollectPaymentCommand;

import javax.persistence.*;
import java.time.LocalDateTime;

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
	private LocalDateTime paymentDate;
	private boolean successful;

	private String errorMessage;

	public PaymentTransaction(CollectPaymentCommand command) {
		this.type = CASH;
		this.cashierId = command.getCashierId();
		this.paymentDate = LocalDateTime.now();
		this.successful = true;
	}

	public PaymentTransaction(ChargeResult chargeResult) {
		type = CREDIT_CARD;
		paymentDate = LocalDateTime.now();
		successful = chargeResult.isPaid();
		errorMessage = chargeResult.getErrorMessage();
	}

	private PaymentTransaction() {
	}

}
