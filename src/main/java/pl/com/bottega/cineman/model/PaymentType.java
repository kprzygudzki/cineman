package pl.com.bottega.cineman.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PaymentType {

	@JsonProperty("cash")
	CASH,
	@JsonProperty("credit_card")
	CREDIT_CARD

}
