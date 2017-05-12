package pl.com.bottega.cineman.model;

import org.junit.Before;
import org.junit.Test;
import pl.com.bottega.cineman.model.commands.Calendar;
import pl.com.bottega.cineman.model.commands.CreateCinemaCommand;
import pl.com.bottega.cineman.model.commands.CreateMovieCommand;
import pl.com.bottega.cineman.model.commands.CreateShowingsCommand;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ShowingFactoryTest {

	private ShowingFactory showingFactory = new ShowingFactory();

	private Cinema cinema;
	private Movie movie;
	private List<LocalDateTime> dates = new LinkedList<>();
	private Calendar calendar = new Calendar();

	private LocalDateTime localDateTime =
			LocalDateTime.of(2017, 4, 9, 14, 35);
	private LocalDateTime anotherLocalDateTime =
			LocalDateTime.of(2017, 4, 9, 17, 50);

	@Before
	public void setUp() {
		movie = new Movie(prepareCreateMovieCommand());
		cinema = new Cinema(prepareCreateCinemaCommand());
	}

	@Test
	public void shouldRememberCinema() {
		dates.add(localDateTime);
		CreateShowingsCommand command = prepareCreateShowingsCommandWithDate();
		//when
		List<Showing> showings = showingFactory.createShowings(command, cinema, movie);
		//then
		assertThat(showings.get(0).getCinema()).isEqualTo(cinema);
	}

	@Test
	public void shouldRememberMovie() {
		dates.add(localDateTime);
		CreateShowingsCommand command = prepareCreateShowingsCommandWithDate();
		//when
		List<Showing> showings = showingFactory.createShowings(command, cinema, movie);
		//then
		assertThat(showings.get(0).getMovie()).isEqualTo(movie);
	}

	@Test
	public void shouldRememberDateTime() {
		dates.add(localDateTime);
		CreateShowingsCommand command = prepareCreateShowingsCommandWithDate();
		//when
		List<Showing> showings = showingFactory.createShowings(command, cinema, movie);
		//then
		assertThat(showings.get(0).getBeginsAt()).isEqualTo(localDateTime);
	}

	@Test
	public void shouldCreateOneShowingByDate() {
		dates.add(localDateTime);
		CreateShowingsCommand command = prepareCreateShowingsCommandWithDate();
		//when
		List<Showing> showings = showingFactory.createShowings(command, cinema, movie);
		//then
		assertThat(showings.size()).isEqualTo(1);
	}

	@Test
	public void shouldCreateMultipleShowingsByDate() {
		dates.add(localDateTime);
		dates.add(anotherLocalDateTime);
		CreateShowingsCommand command = prepareCreateShowingsCommandWithDate();
		command.setDates(dates);
		//when
		List<Showing> showings = showingFactory.createShowings(command, cinema, movie);
		//then
		assertThat(showings.size()).isEqualTo(2);
	}

	@Test
	public void shouldCreateShowingsByCalendar() {
		LocalDateTime fromDate = LocalDateTime.of(2017, 4, 10, 8, 0);
		LocalDateTime untilDate = LocalDateTime.of(2017, 4, 14, 20, 0);
		CreateShowingsCommand command = prepareCreateShowingsCommandWithCalendar(fromDate, untilDate);
		//when
		List<Showing> showings = showingFactory.createShowings(command, cinema, movie);
		//then
		assertThat(showings.size()).isEqualTo(6);
	}

	@Test
	public void shouldCreateShowingsByCalendarAndNotExceedBounds() {
		LocalDateTime fromDate = LocalDateTime.of(2017, 4, 10, 20, 0);
		LocalDateTime untilDate = LocalDateTime.of(2017, 4, 14, 8, 0);
		CreateShowingsCommand command = prepareCreateShowingsCommandWithCalendar(fromDate, untilDate);
		//when
		List<Showing> showings = showingFactory.createShowings(command, cinema, movie);
		//then
		assertThat(showings.size()).isEqualTo(2);
	}

	@Test
	public void shouldNotCreateShowingsWhenGivenBothDatesAndCalendar() {
		CreateShowingsCommand command = new CreateShowingsCommand();
		command.setCalendar(calendar);
		command.setDates(dates);
		//when
		List<Showing> showings = showingFactory.createShowings(command, cinema, movie);
		//then
		assertThat(showings).isEmpty();
	}

	private CreateShowingsCommand prepareCreateShowingsCommandWithDate() {
		CreateShowingsCommand command = new CreateShowingsCommand();
		command.setDates(dates);
		return command;
	}

	private CreateShowingsCommand prepareCreateShowingsCommandWithCalendar(
			LocalDateTime fromDate, LocalDateTime untilDate) {
		CreateShowingsCommand command = new CreateShowingsCommand();
		calendar.setFromDate(fromDate);
		calendar.setUntilDate(untilDate);
		Set<DayOfWeek> daysOfWeek = new HashSet<>();
		daysOfWeek.add(DayOfWeek.MONDAY);
		daysOfWeek.add(DayOfWeek.WEDNESDAY);
		daysOfWeek.add(DayOfWeek.FRIDAY);
		calendar.setWeekDays(daysOfWeek);
		Set<LocalTime> times = new HashSet<>();
		times.add(LocalTime.of(14, 35));
		times.add(LocalTime.of(17, 50));
		calendar.setHours(times);
		command.setCalendar(calendar);
		return command;
	}

	private CreateCinemaCommand prepareCreateCinemaCommand() {
		String city = "Lublin";
		String name = "Felicity";
		CreateCinemaCommand command = new CreateCinemaCommand();
		command.setCity(city);
		command.setName(name);
		return command;
	}

	private CreateMovieCommand prepareCreateMovieCommand() {
		String title = "Pulp Fiction";
		String description = "You have to see this";
		Set<String> genres = new HashSet<>();
		genres.add("Crime");
		genres.add("Drama");
		Set<String> actors = new HashSet<>();
		actors.add("Samuel L. Jackson");
		actors.add("Uma Thurman");
		Integer minAge = 16;
		Integer length = 178;
		CreateMovieCommand command = new CreateMovieCommand();
		command.setTitle(title);
		command.setDescription(description);
		command.setGenres(genres);
		command.setActors(actors);
		command.setMinAge(minAge);
		command.setLength(length);
		return command;
	}

}