package pl.com.bottega.cineman.model.commands;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

public class Calendar {

	private LocalDateTime fromDate;
	private LocalDateTime untilDate;
	private Set<DayOfWeek> daysOfWeek;
	private Set<LocalTime> times;

	public Calendar() {
	}

	public LocalDateTime getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDateTime fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDateTime getUntilDate() {
		return untilDate;
	}

	public void setUntilDate(LocalDateTime untilDate) {
		this.untilDate = untilDate;
	}

	public Set<DayOfWeek> getDaysOfWeek() {
		return daysOfWeek;
	}

	public void setDaysOfWeek(Set<DayOfWeek> daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}

	public Set<LocalTime> getTimes() {
		return times;
	}

	public void setTimes(Set<LocalTime> times) {
		this.times = times;
	}

}
