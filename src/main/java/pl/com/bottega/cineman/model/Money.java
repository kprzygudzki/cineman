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
		return new Money(value.multiply(new BigDecimal(multiplicand)));
	}

	public Money divide(int divisor){
		return new Money(value.divide(new BigDecimal(divisor)));
	}

	public Money add(Money summand) {
		return new Money(value.add(summand.value));
	}

	public Money subtract(Money subtrahend){
		return new Money(value.subtract(subtrahend.value));
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
		return this.value.compareTo(compareMoney.value);
	}

}
