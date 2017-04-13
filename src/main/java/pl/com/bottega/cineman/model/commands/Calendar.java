package pl.com.bottega.cineman.model.commands;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

public class Calendar implements Validatable {

	private LocalDateTime fromDate;
	private LocalDateTime untilDate;
	private Set<DayOfWeek> weekDays;
	private Set<LocalTime> hours;

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

	public Set<DayOfWeek> getWeekDays() {
		return weekDays;
	}

	public void setWeekDays(Set<DayOfWeek> weekDays) {
		this.weekDays = weekDays;
	}

	public Set<LocalTime> getHours() {
		return hours;
	}

	public void setHours(Set<LocalTime> hours) {
		this.hours = hours;
	}

	@Override
	public void validate(ValidationErrors errors) {
		if (fromDate == null)
			errors.add("fromDate", "is a required field and cannot be blank");
		if (untilDate == null)
			errors.add("untilDate", "is a required field and cannot be blank");
		if (weekDays == null || weekDays.isEmpty())
			errors.add("weekDays", "is a required field and cannot be blank");
		if (hours == null || hours.isEmpty())
			errors.add("times", "is a required field and cannot be blank");
	}

}
