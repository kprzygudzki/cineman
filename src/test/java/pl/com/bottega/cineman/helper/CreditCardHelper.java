package pl.com.bottega.cineman.helper;

import pl.com.bottega.cineman.model.CreditCard;

import java.time.Month;
import java.time.Year;

public class CreditCardHelper {
	
	private final static long SUCCESSFUL_VISA_CARD_NUMBER = 4242424242424242L;
	private final static long INCORRECT_CARD_NUMBER = 4242424242424241L;
	private final static long DECLINED_CARD_NUMBER = 4000000000000002L;
	private final static long EXPIRED_CARD_NUMBER = 4000000000000069L;
	private final static long INCORRECT_CVC_CARD_NUMBER = 4000000000000127L;
	private final static long PROCESSING_ERROR_CARD_NUMBER = 4000000000000119L;

	private final static int VALID_CVC_NUMBER = 123;
	private final static int INVALID_CVC_NUMBER = 99;

	private final static Year INVALID_EXPIRATION_YEAR = Year.of(1970);
	
	public static CreditCard createValidCreditCard() {
		CreditCard creditCard = new CreditCard();
		creditCard.setNumber(SUCCESSFUL_VISA_CARD_NUMBER);
		creditCard.setCvc(VALID_CVC_NUMBER);
		creditCard.setExpirationMonth(Month.DECEMBER);
		creditCard.setExpirationYear(Year.now());
		return creditCard;
	}

	public static long getSuccessfulVisaCardNumber() {
		return SUCCESSFUL_VISA_CARD_NUMBER;
	}

	public static long getIncorrectCardNumber() {
		return INCORRECT_CARD_NUMBER;
	}

	public static long getIncorrectCvcCardNumber() {
		return INCORRECT_CVC_CARD_NUMBER;
	}

	public static long getExpiredCardNumber() {
		return EXPIRED_CARD_NUMBER;
	}

	public static long getDeclinedCardNumber() {
		return DECLINED_CARD_NUMBER;
	}

	public static long getProcessingErrorCardNumber() {
		return PROCESSING_ERROR_CARD_NUMBER;
	}

	public static int getValidCvcNumber() {
		return VALID_CVC_NUMBER;
	}

	public static int getInvalidCvcNumber() {
		return INVALID_CVC_NUMBER;
	}

	public static Year getInvalidExpirationYear() {
		return INVALID_EXPIRATION_YEAR;
	}
}
