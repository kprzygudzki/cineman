package pl.com.bottega.cineman.model;

import pl.com.bottega.cineman.model.commands.Calendar;
import pl.com.bottega.cineman.model.commands.CreateShowingsCommand;

import java.time.*;
import java.util.EnumSet;
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
		Set<DayOfWeek> daysOfWeek = calendar.getDaysOfWeek();
		Set<LocalTime> times = calendar.getTimes();

		LocalDate fromDate = fromDateTime.toLocalDate();
		LocalDate untilDate = untilDateTime.toLocalDate();
		LocalDateTime currentDateTime;

		for (LocalDate currentDate = fromDate; !currentDate.isAfter(untilDate); currentDate = currentDate.plusDays(1))
			if (daysOfWeek.contains(currentDate.getDayOfWeek()))
				for (LocalTime time : times) {
					currentDateTime = LocalDateTime.of(currentDate, time);
					if (doesNotExceedBounds(fromDateTime, untilDateTime, currentDateTime))
						result.add(new Showing(cinema, movie, currentDateTime));
				}
		return result;
	}

	private boolean doesNotExceedBounds(LocalDateTime fromDateTime, LocalDateTime untilDateTime, LocalDateTime currentDateTime) {
		return !currentDateTime.isBefore(fromDateTime) && !currentDateTime.isAfter(untilDateTime);
	}

}
