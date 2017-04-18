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

	private static final String REQUIRED_FIELD = "is a required field and cannot be blank";

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
			errors.add("fromDate", REQUIRED_FIELD);
		if (untilDate == null)
			errors.add("untilDate", REQUIRED_FIELD);
		if (weekDays == null || weekDays.isEmpty())
			errors.add("weekDays", REQUIRED_FIELD);
		if (hours == null || hours.isEmpty())
			errors.add("times", REQUIRED_FIELD);
	}

}
