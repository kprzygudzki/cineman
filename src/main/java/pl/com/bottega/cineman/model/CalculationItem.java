package pl.com.bottega.cineman.model;

import java.math.BigDecimal;

public class CalculationItem {

	private String kind;
	private Integer count;
	private BigDecimal unitPrice;
	private BigDecimal totalPrice;

	private CalculationItem(String kind, Integer count, BigDecimal unitPrice, BigDecimal totalPrice) {
		this.kind = kind;
		this.count = count;
		this.unitPrice = unitPrice;
		this.totalPrice = totalPrice;
	}

	public static CalculationItem of(ReservationItem reservationItem, BigDecimal unitPrice) {
		String kind = reservationItem.getKind();
		Integer count = reservationItem.getCount();
		BigDecimal totalPrice = calculateTotalPrice(reservationItem.getCount(), unitPrice);
		return new CalculationItem(kind, count, unitPrice, totalPrice);
	}

	private static BigDecimal calculateTotalPrice(Integer count, BigDecimal unitPrice) {
		return unitPrice.multiply(BigDecimal.valueOf(count));
	}

	private BigDecimal calculateTotalPrice(ReservationItem reservationItemCount, BigDecimal reservationItemUnitPrice) {
		return reservationItemUnitPrice.multiply(BigDecimal.valueOf(reservationItemCount.getCount()));
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
