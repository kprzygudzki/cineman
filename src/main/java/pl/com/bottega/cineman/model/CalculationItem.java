package pl.com.bottega.cineman.model;

import java.math.BigDecimal;

public class CalculationItem {

	private String kind;
	private Integer count;
	private BigDecimal unitPrice;
	private BigDecimal totalPrice;

	public CalculationItem(ReservationItem reservationItem, BigDecimal unitPrice) {
		this.kind = reservationItem.getKind();
		this.count = reservationItem.getQuantity();
		this.unitPrice = unitPrice;
		this.totalPrice = calculateTotalPrice(reservationItem, unitPrice);
	}

	private BigDecimal calculateTotalPrice(ReservationItem reservationItemCount, BigDecimal reservationItemUnitPrice) {
		return reservationItemUnitPrice.multiply(BigDecimal.valueOf(reservationItemCount.getQuantity()));
	}

	public String getKind() {
		return kind;
	}

	public Integer getCount() {
		return count;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

}
