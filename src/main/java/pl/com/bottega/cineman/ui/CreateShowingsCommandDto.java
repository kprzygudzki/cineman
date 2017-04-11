package pl.com.bottega.cineman.ui;

import com.fasterxml.jackson.annotation.JsonFormat;
import pl.com.bottega.cineman.model.commands.Calendar;
import pl.com.bottega.cineman.model.commands.CreateShowingsCommand;

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
	private CalendarDto calendar = new CalendarDto();

	public CreateShowingsCommandDto() {
	}

	public CreateShowingsCommand getCreateShowingsCommand() {
		CreateShowingsCommand command = new CreateShowingsCommand();
		command.setCinemaId(cinemaId);
		command.setMovieId(movieId);
		command.setDates(dates);

		Calendar calendar = new Calendar();
		calendar.setFromDate(this.calendar.fromDate);
		calendar.setUntilDate(this.calendar.untilDate);
		Set<DayOfWeek> daysOfWeek = new HashSet<>();
		for (String s : this.calendar.weekDays)
			daysOfWeek.add(DayOfWeek.valueOf(s.toUpperCase()));
		calendar.setWeekDays(daysOfWeek);
		calendar.setHours(this.calendar.hours);
		command.setCalendar(calendar);
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
