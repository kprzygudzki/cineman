package pl.com.bottega.cineman.infrastructure;

import pl.com.bottega.cineman.model.ChargeResult;
import pl.com.bottega.cineman.model.CreditCard;
import pl.com.bottega.cineman.model.PaymentFacade;

import java.math.BigDecimal;

public class StripePaymentFacade implements PaymentFacade {

	@Override
	public ChargeResult charge(CreditCard creditCard, BigDecimal amount) {
		return null;
	}

}
