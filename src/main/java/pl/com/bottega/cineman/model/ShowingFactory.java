package pl.com.bottega.cineman.model;

import pl.com.bottega.cineman.model.commands.Calendar;
import pl.com.bottega.cineman.model.commands.CreateShowingsCommand;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class ShowingFactory {

	public ShowingFactory() {
	}

	public List<Showing> createShowings(CreateShowingsCommand command, Cinema cinema, Movie movie) {
		List<Showing> showings = new LinkedList<>();
		if (command.getDates() == null && command.getCalendar() != null)
			showings = createShowingsForCalendar(command, cinema, movie);
		else if (command.getCalendar() == null && command.getDates() != null)
			showings = createShowingsForDates(command, cinema, movie);
		return showings;
	}

	private List<Showing> createShowingsForDates(CreateShowingsCommand command, Cinema cinema, Movie movie) {
		List<Showing> result = new LinkedList<>();
		List<LocalDateTime> dateTimes = command.getDates();
		for (LocalDateTime dateTime : dateTimes)
			result.add(new Showing(cinema, movie, dateTime));
		return result;
	}

	private List<Showing> createShowingsForCalendar(CreateShowingsCommand command, Cinema cinema, Movie movie) {
		List<Showing> result = new LinkedList<>();
		Calendar calendar = command.getCalendar();

		LocalDateTime fromDateTime = calendar.getFromDate();
		LocalDateTime untilDateTime = calendar.getUntilDate();
		Set<DayOfWeek> daysOfWeek = calendar.getWeekDays();
		Set<LocalTime> times = calendar.getHours();

		LocalDate fromDate = fromDateTime.toLocalDate();
		LocalDate untilDate = untilDateTime.toLocalDate();
		LocalDateTime processedDateTime;

		for (LocalDate processedDate = fromDate; !processedDate.isAfter(untilDate); processedDate = processedDate.plusDays(1))
			if (daysOfWeek.contains(processedDate.getDayOfWeek()))
				for (LocalTime time : times) {
					processedDateTime = LocalDateTime.of(processedDate, time);
					if (doesNotExceedBounds(fromDateTime, untilDateTime, processedDateTime))
						result.add(new Showing(cinema, movie, processedDateTime));
				}
		return result;
	}

	private boolean doesNotExceedBounds(LocalDateTime fromDateTime, LocalDateTime untilDateTime, LocalDateTime currentDateTime) {
		return !currentDateTime.isBefore(fromDateTime) && !currentDateTime.isAfter(untilDateTime);
	}

}
