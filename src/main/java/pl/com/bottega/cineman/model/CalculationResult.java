package pl.com.bottega.cineman.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class CalculationResult {

	private Collection<CalculationItem> calculationItems;
	private BigDecimal totalPrice;

	private CalculationResult(Collection<CalculationItem> calculationItems, BigDecimal totalPrice) {
		this.calculationItems = calculationItems;
		this.totalPrice = totalPrice;
	}

	public static CalculationResult of(Collection<CalculationItem> calculationItems) {
		BigDecimal totalPrice = calculateTotalPrice(calculationItems);
		return new CalculationResult(new LinkedList<>(calculationItems), totalPrice);
	}

	private static BigDecimal calculateTotalPrice(Collection<CalculationItem> calculationItems) {
		BigDecimal totalPrice = BigDecimal.ZERO;
		for (CalculationItem calculationItem : calculationItems)
			totalPrice = totalPrice.add(calculationItem.getTotalPrice());
		return totalPrice;
	}

	public Collection<CalculationItem> getCalculationItems() {
		return Collections.unmodifiableCollection(calculationItems);
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

}
