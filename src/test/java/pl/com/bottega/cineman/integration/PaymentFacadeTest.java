package pl.com.bottega.cineman.integration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.com.bottega.cineman.model.ChargeResult;
import pl.com.bottega.cineman.model.CreditCard;
import pl.com.bottega.cineman.model.PaymentFacade;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.com.bottega.cineman.helper.CreditCardHelper.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PaymentFacadeTest {

	@Autowired
	private PaymentFacade paymentFacade;

	private CreditCard creditCard;
	private BigDecimal amount;

	@Before
	public void setUp() {
		creditCard = createValidCreditCard();
		amount = BigDecimal.valueOf(10);
	}

	@Test
	public void shouldChargeSuccessfully() {
		//when
		ChargeResult charge = paymentFacade.charge(creditCard, amount);
		//then
		assertThat(charge.isPaid()).isTrue();
		assertThat(charge.getStatus()).isEqualTo("succeeded");
	}

	@Test
	public void shouldFailWithIncorrectCardNumber() {
		//given
		creditCard.setNumber(getIncorrectCardNumber());
		//when
		ChargeResult charge = paymentFacade.charge(creditCard, amount);
		//then
		assertThat(charge.isPaid()).isFalse();
		assertThat(charge.getStatus()).isEqualTo("failed");
		assertThat(charge.getErrorMessage()).isEqualTo("Your card number is incorrect.");
	}

	@Test
	public void shouldFailWithDeclinedCard() {
		//given
		creditCard.setNumber(getDeclinedCardNumber());
		//when
		ChargeResult charge = paymentFacade.charge(creditCard, amount);
		//then
		assertThat(charge.isPaid()).isFalse();
		assertThat(charge.getStatus()).isEqualTo("failed");
		assertThat(charge.getErrorMessage()).isEqualTo("Your card was declined.");
	}

	@Test
	public void shouldFailWithIncorrectCvc() {
		//given
		creditCard.setNumber(getIncorrectCvcCardNumber());
		//when
		ChargeResult charge = paymentFacade.charge(creditCard, amount);
		//then
		assertThat(charge.isPaid()).isFalse();
		assertThat(charge.getStatus()).isEqualTo("failed");
		assertThat(charge.getErrorMessage()).isEqualTo("Your card's security code is incorrect.");
	}

	@Test
	public void shouldFailWithExpiredCard() {
		//given
		creditCard.setNumber(getExpiredCardNumber());
		//when
		ChargeResult charge = paymentFacade.charge(creditCard, amount);
		//then
		assertThat(charge.isPaid()).isFalse();
		assertThat(charge.getStatus()).isEqualTo("failed");
		assertThat(charge.getErrorMessage()).isEqualTo("Your card has expired.");
	}

	@Test
	public void shouldFailWithInvalidCvc() {
		//given
		creditCard.setCvc(getInvalidCvcNumber());
		//when
		ChargeResult charge = paymentFacade.charge(creditCard, amount);
		//then
		assertThat(charge.isPaid()).isFalse();
		assertThat(charge.getStatus()).isEqualTo("failed");
		assertThat(charge.getErrorMessage()).isEqualTo("Your card's security code is invalid.");
	}

	@Test
	public void shouldFailWithInvalidExpirationYear() {
		//given
		creditCard.setExpirationYear(getInvalidExpirationYear());
		//when
		ChargeResult charge = paymentFacade.charge(creditCard, amount);
		//then
		assertThat(charge.isPaid()).isFalse();
		assertThat(charge.getStatus()).isEqualTo("failed");
		assertThat(charge.getErrorMessage()).isEqualTo("Your card's expiration year is invalid.");
	}

	@Test
	public void shouldFailWithProcessingError() {
		//given
		creditCard.setNumber(getProcessingErrorCardNumber());
		//when
		ChargeResult charge = paymentFacade.charge(creditCard, amount);
		//then
		assertThat(charge.isPaid()).isFalse();
		assertThat(charge.getStatus()).isEqualTo("failed");
		assertThat(charge.getErrorMessage())
				.isEqualTo("An error occurred while processing your card. Try again in a little bit.");
	}

}
