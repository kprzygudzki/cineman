package pl.com.bottega.cineman.ui;

import com.fasterxml.jackson.annotation.JsonFormat;
import pl.com.bottega.cineman.model.commands.Calendar;
import pl.com.bottega.cineman.model.commands.CreateShowingsCommand;
import pl.com.bottega.cineman.model.commands.InvalidCommandException;
import pl.com.bottega.cineman.model.commands.Validatable;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CreateShowingsCommandDto {

	private Long cinemaId;
	private Long movieId;
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm")
	private List<LocalDateTime> dates;
	private CalendarDto calendar;

	public CreateShowingsCommandDto() {
	}

	CreateShowingsCommand getCreateShowingsCommand() {
		CreateShowingsCommand command = new CreateShowingsCommand();
		command.setCinemaId(cinemaId);
		command.setMovieId(movieId);
		command.setDates(dates);
		if (this.calendar != null) {
			Calendar calendar = this.calendar.getCalendar();
			command.setCalendar(calendar);
		}
		return command;
	}

	public void setCinemaId(Long cinemaId) {
		this.cinemaId = cinemaId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public void setDates(List<LocalDateTime> dates) {
		this.dates = dates;
	}

	public void setCalendar(CalendarDto calendar) {
		this.calendar = calendar;
	}

	public class CalendarDto {

		@JsonFormat(pattern = "yyyy/MM/dd HH:mm")
		private LocalDateTime fromDate;
		@JsonFormat(pattern = "yyyy/MM/dd HH:mm")
		private LocalDateTime untilDate;
		private Set<String> weekDays;
		private Set<LocalTime> hours;

		Calendar getCalendar() {
			Calendar calendar = new Calendar();
			calendar.setFromDate(this.fromDate);
			calendar.setUntilDate(this.untilDate);

			if (weekDays != null) {
				Set<DayOfWeek> daysOfWeek = new HashSet<>();
				for (String s : this.weekDays) {
					try {
						DayOfWeek dayOfWeek = DayOfWeek.valueOf(s.toUpperCase());
						daysOfWeek.add(dayOfWeek);
					} catch (IllegalArgumentException ex) {
						Validatable.ValidationErrors errors = new Validatable.ValidationErrors();
						errors.add("weekDays", String.format("%s is not a valid day of week", s));
						throw new InvalidCommandException(errors);
					}
				}
				calendar.setWeekDays(daysOfWeek);
			}
			calendar.setHours(this.hours);
			return calendar;
		}

		public void setFromDate(LocalDateTime fromDate) {
			this.fromDate = fromDate;
		}

		public void setUntilDate(LocalDateTime untilDate) {
			this.untilDate = untilDate;
		}

		public void setWeekDays(Set<String> weekDays) {
			this.weekDays = weekDays;
		}

		public void setHours(Set<LocalTime> hours) {
			this.hours = hours;
		}

	}

}
