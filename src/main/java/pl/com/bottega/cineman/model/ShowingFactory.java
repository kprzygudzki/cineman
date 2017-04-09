package pl.com.bottega.cineman.model;

import pl.com.bottega.cineman.model.commands.Calendar;
import pl.com.bottega.cineman.model.commands.CreateShowingsCommand;

import java.time.*;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class ShowingFactory {

	private CinemaRepository cinemaRepository;
	private MovieRepository movieRepository;

	public ShowingFactory(CinemaRepository cinemaRepository, MovieRepository movieRepository) {
		this.cinemaRepository = cinemaRepository;
		this.movieRepository = movieRepository;
	}

	public List<Showing> createShowings(CreateShowingsCommand command) {
		if (command.getDates() == null && command.getCalendar() != null)
			return createShowingsForCalendar(command);
		else if (command.getCalendar() == null && command.getDates() != null)
			return createShowingsForDates(command);
		else
			return null;
	}

	private List<Showing> createShowingsForDates(CreateShowingsCommand command) {
		List<Showing> result = new LinkedList<>();
		Long cinemaId = command.getCinemaId();
		Cinema cinema = cinemaRepository.get(cinemaId);
		Long movieId = command.getMovieId();
		Movie movie = movieRepository.get(movieId);
		List<LocalDateTime> dateTimes = command.getDates();
		for (LocalDateTime dateTime : dateTimes)
			result.add(new Showing(cinema, movie, dateTime));
		return result;
	}

	private List<Showing> createShowingsForCalendar(CreateShowingsCommand command) {
		List<Showing> result = new LinkedList<>();
		Long cinemaId = command.getCinemaId();
		Cinema cinema = cinemaRepository.get(cinemaId);
		Long movieId = command.getMovieId();
		Movie movie = movieRepository.get(movieId);
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
