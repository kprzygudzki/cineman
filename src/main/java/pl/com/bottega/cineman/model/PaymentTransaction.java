package pl.com.bottega.cineman.model;

import pl.com.bottega.cineman.model.commands.CollectPaymentCommand;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class PaymentTransaction {

	@Id
	@GeneratedValue
	private Long id;

	@Enumerated
	private PaymentType type;
	private Long cashierId;
	private LocalDateTime paymentDate;
	private boolean successful;
	private String errorMessage;

	public PaymentTransaction(CollectPaymentCommand command) {
		this.type = command.getType();
		this.cashierId = command.getCashierId();
		this.paymentDate = LocalDateTime.now();
		this.successful = true;
	}
}