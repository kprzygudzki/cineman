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
		validateFromDate(errors);
		validateUntilDate(errors);
		validateWeekDays(errors);
		validateHours(errors);
	}

	private void validateFromDate(ValidationErrors errors) {
		if (fromDate == null)
			errors.add("fromDate", REQUIRED_FIELD);
		else if (fromDate.isBefore(LocalDateTime.now()))
			errors.add("fromDate", FUTURE_DATE_REQUIRED);
	}

	private void validateUntilDate(ValidationErrors errors) {
		if (untilDate == null)
			errors.add("untilDate", REQUIRED_FIELD);
		else if (untilDate.isBefore(LocalDateTime.now()))
			errors.add("untilDate", FUTURE_DATE_REQUIRED);
	}

	private void validateWeekDays(ValidationErrors errors) {
		if (weekDays == null || weekDays.isEmpty())
			errors.add("weekDays", REQUIRED_FIELD);
		else if (weekDays.remove(null))
			errors.add("weekDays", NON_NULL_ELEMENT);
	}

	private void validateHours(ValidationErrors errors) {
		if (hours == null || hours.isEmpty())
			errors.add("times", REQUIRED_FIELD);
		else if (hours.remove(null))
			errors.add("times", NON_NULL_ELEMENT);
	}

}
