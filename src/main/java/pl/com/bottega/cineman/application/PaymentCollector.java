package pl.com.bottega.cineman.application;

import pl.com.bottega.cineman.model.commands.CollectPaymentCommand;

public interface PaymentCollector {

	void collectPayment(CollectPaymentCommand command);

}
