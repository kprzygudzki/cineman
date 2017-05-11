package pl.com.bottega.cineman.model;

public class ChargeResult {

	private String errorMessage;
	private String chargeId;
	private Long amount;
	private String currency;
	private boolean paid;
	private String status;
	private Long createdAt;

	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}

	public String getChargeId() {
		return chargeId;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getAmount() {
		return amount;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCurrency() {
		return currency;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}

	public Boolean isPaid() {
		return paid;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
