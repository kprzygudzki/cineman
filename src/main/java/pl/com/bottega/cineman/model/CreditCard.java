package pl.com.bottega.cineman.model;

import pl.com.bottega.cineman.model.commands.Validatable;

import java.time.Month;
import java.time.Year;

public class CreditCard implements Validatable {

	private Long number;
	private Month expirationMonth;
	private Year expirationYear;
	private int cvc;

	public CreditCard() {
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Month getExpirationMonth() {
		return expirationMonth;
	}

	public void setExpirationMonth(Month expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	public Year getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(Year expirationYear) {
		this.expirationYear = expirationYear;
	}

	public int getCvc() {
		return cvc;
	}

	public void setCvc(int cvc) {
		this.cvc = cvc;
	}

	@Override
	public void validate(ValidationErrors errors) {
	}

}
