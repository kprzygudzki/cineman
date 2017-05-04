package pl.com.bottega.cineman.model;

import java.math.BigDecimal;
import java.util.Collection;

public class CalculationResult {

	private Collection<CalculationItem> calculationItems;
	private BigDecimal totalPrice;

	public CalculationResult(Collection<CalculationItem> calculationItems) {
		this.calculationItems = calculationItems;
		this.totalPrice = calculateTotalPrice(calculationItems);
	}

	private BigDecimal calculateTotalPrice(Collection<CalculationItem> calculationItems) {
		BigDecimal totalPrice = BigDecimal.ZERO;

		for (CalculationItem calculationItem : calculationItems) {
			totalPrice = totalPrice.add(calculationItem.getTotalPrice());
		}

		return totalPrice;
	}

	public Collection<CalculationItem> getCalculationItems() {
		return calculationItems;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

}
