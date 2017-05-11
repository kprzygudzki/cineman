package pl.com.bottega.cineman.model;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;

public class CalculationResult {

	private Set<CalculationItem> calculationItems;
	private BigDecimal totalPrice;

	private CalculationResult(Set<CalculationItem> calculationItems, BigDecimal totalPrice) {
		this.calculationItems = calculationItems;
		this.totalPrice = totalPrice;
	}

	public static CalculationResult of(Set<CalculationItem> calculationItems) {
		BigDecimal totalPrice = calculateTotalPrice(calculationItems);
		return new CalculationResult(calculationItems, totalPrice);
	}

	private static BigDecimal calculateTotalPrice(Set<CalculationItem> calculationItems) {
		BigDecimal totalPrice = BigDecimal.ZERO;
		for (CalculationItem calculationItem : calculationItems)
			totalPrice = totalPrice.add(calculationItem.getTotalPrice());
		return totalPrice;
	}

	public Set<CalculationItem> getCalculationItems() {
		return Collections.unmodifiableSet(calculationItems);
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

}
