package pl.com.bottega.cineman.model;

import java.math.BigDecimal;

public interface PaymentFacade {

	ChargeResult charge(CreditCard creditCard, BigDecimal amount);

}
