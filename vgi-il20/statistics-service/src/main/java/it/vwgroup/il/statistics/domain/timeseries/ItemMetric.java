package it.vwgroup.il.statistics.domain.timeseries;

import java.math.BigDecimal;

import it.vwgroup.il.statistics.domain.Currency;
import it.vwgroup.il.statistics.domain.TimePeriod;

/**
 * Represents normalized {@link it.vwgroup.il.statistics.domain.Item} object
 * with {@link Currency#getBase()} currency and {@link TimePeriod#getBase()} time period
 */
public class ItemMetric {

	private String title;

	private BigDecimal amount;

	public ItemMetric(String title, BigDecimal amount) {
		this.title = title;
		this.amount = amount;
	}

	public String getTitle() {
		return title;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ItemMetric that = (ItemMetric) o;

		return title.equalsIgnoreCase(that.title);

	}

	@Override
	public int hashCode() {
		return title.hashCode();
	}
}
