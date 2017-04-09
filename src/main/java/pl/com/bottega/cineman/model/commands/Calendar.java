package pl.com.bottega.cineman.model.commands;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

public class Calendar implements Validatable {

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

	@Override
	public void validate(ValidationErrors errors) {
		if (fromDate == null)
			errors.add("fromDate", "cannot be blank");
		if (untilDate == null)
			errors.add("untilDate", "cannot be blank");
		if (daysOfWeek == null || daysOfWeek.isEmpty())
			errors.add("daysOfWeek", "cannot be empty");
		if (times == null || times.isEmpty())
			errors.add("times", "cannot be empty");
	}

}
