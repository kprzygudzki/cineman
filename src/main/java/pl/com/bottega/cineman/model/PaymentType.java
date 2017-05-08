package pl.com.bottega.cineman.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentType {

	CASH("cash");

	private String description;

	PaymentType(String description) {
		this.description = description;
	}

	@JsonValue
	public String getDescription() {
		return description;
	}
}
