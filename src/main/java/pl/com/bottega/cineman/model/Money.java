package pl.com.bottega.cineman.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;

@Embeddable
public class Money implements Comparable<Money>, Serializable {

	public static final Money ZERO = new Money(BigDecimal.ZERO);

	private BigDecimal value;

	public Money() {

	}

	public Money(double value) {
		this.value = BigDecimal.valueOf(value);
	}

	public Money(long value) {
		this.value = BigDecimal.valueOf(value);
	}

	public Money(int wholePart, int fractionPart) {
		this.value = BigDecimal.valueOf(wholePart + fractionPart / 100);
	}

	public Money(String value) {
		this.value = new BigDecimal(value);
	}

	public Money(int value) {
		this.value = new BigDecimal(value);
	}

	public Money(BigDecimal newValue) {
		this.value = newValue;
	}

	public Money multiply(long multiplicand) {
		BigDecimal newValue = value.multiply(new BigDecimal(multiplicand));
		return new Money(newValue);
	}

	public Money divide(int divider){
		BigDecimal newValue = value.divide(new BigDecimal(divider));
		return new Money(newValue);
	}

	public Money add(Money summand) {
		BigDecimal sum = value.add(summand.value);
		return new Money(sum);
	}

	public Money subtract(Money subtrahend){
		BigDecimal sum = this.value.subtract(subtrahend.value);
		return new Money(sum);
	}

	public boolean greaterThan(Money comparator){
		return value.compareTo(comparator.value) == 1;
	}

	public boolean greaterEquals(Money comparator){
		return value.compareTo(comparator.value) == 1 || value.compareTo(comparator.value) == 0;
	}

	public boolean lessThan(Money comparator){
		return value.compareTo(comparator.value) == -1;
	}

	public boolean lessEquals(Money comparator){
		return value.compareTo(comparator.value) == -1 || value.compareTo(comparator.value) == 0;
	}

	private int getIntValue() {
		return value.intValue();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Money)) return false;

		Money money = (Money) o;

		return value.equals(money.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	@Override
	public int compareTo(Money compareMoney) {
		//descending order
		return compareMoney.getIntValue() - this.getIntValue();
	}
}
