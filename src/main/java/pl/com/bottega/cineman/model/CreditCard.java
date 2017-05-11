package pl.com.bottega.cineman.model;

import pl.com.bottega.cineman.model.commands.Validatable;

import java.time.Month;
import java.time.Year;

public class CreditCard implements Validatable {

	private Long number;
	private Month expirationMonth;
	private Year expirationYear;
	private Integer cvc;

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

	public Integer getCvc() {
		return cvc;
	}

	public void setCvc(Integer cvc) {
		this.cvc = cvc;
	}

	@Override
	public void validate(ValidationErrors errors) {
		if (number == null)
			errors.add("number", REQUIRED_FIELD);
		if (expirationMonth == null)
			errors.add("exporationMonth", REQUIRED_FIELD);
		if (expirationYear == null)
			errors.add("expirationYear", REQUIRED_FIELD);
		if (cvc == null)
			errors.add("cvc", REQUIRED_FIELD);
	}

}
