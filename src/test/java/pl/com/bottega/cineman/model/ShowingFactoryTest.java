package pl.com.bottega.cineman.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.com.bottega.cineman.model.commands.Calendar;
import pl.com.bottega.cineman.model.commands.CreateShowingsCommand;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
//@SpringBootTest
public class ShowingFactoryTest {

	private CinemaRepository cinemaRepository = new CinemaRepository() {
		@Override
		public void put(Cinema cinema) {
		}

		@Override
		public Cinema get(Long id) throws CinemaNotFoundException {
//			if (id.equals(1L))
			return cinema;
		}
	};
	private MovieRepository movieRepository = new MovieRepository() {
		@Override
		public void put(Movie movie) {
		}

		@Override
		public Movie get(Long id) {
//			if (id.equals(2L))
			return movie;
		}
	};
	private ShowingFactory showingFactory = new ShowingFactory(cinemaRepository, movieRepository);

	private Cinema cinema = new Cinema();
	private Movie movie = new Movie();
	private List<LocalDateTime> localDateTimes = new LinkedList<>();
	private Calendar calendar = new Calendar();

	private LocalDateTime localDateTime =
			LocalDateTime.of(2017, 4, 9, 14, 35);
	private LocalDateTime anotherLocalDateTime =
			LocalDateTime.of(2017, 4, 9, 17, 50);

	@Test
	public void shouldCreateOneShowingByDate() {
		localDateTimes.add(localDateTime);
		CreateShowingsCommand command = prepareCreateShowingsCommandWithDate();
		//when
		List<Showing> showings = showingFactory.createShowings(command);
		//then
		assertThat(showings.size()).isEqualTo(1);
		assertThat(showings.get(0).getCinema()).isEqualTo(cinema);
		assertThat(showings.get(0).getMovie()).isEqualTo(movie);
		assertThat(showings.get(0).getBeginsAt()).isEqualTo(localDateTime);
	}

	@Test
	public void shouldCreateMultipleShowingsByDate() {
		localDateTimes.add(localDateTime);
		localDateTimes.add(anotherLocalDateTime);
		CreateShowingsCommand command = prepareCreateShowingsCommandWithDate();
		command.setDates(localDateTimes);
		//when
		List<Showing> showings = showingFactory.createShowings(command);
		//then
		assertThat(showings.size()).isEqualTo(2);
	}

	@Test
	public void shouldCreateShowingsByCalendar() {
		LocalDateTime fromDate = LocalDateTime.of(2017, 4, 10, 8, 0);
		LocalDateTime untilDate = LocalDateTime.of(2017, 4, 14, 20, 0);
		CreateShowingsCommand command = prepareCreateShowingsCommandWithCalendar(fromDate, untilDate);
		//when
		List<Showing> showings = showingFactory.createShowings(command);
		//then
		assertThat(showings.size()).isEqualTo(6);
	}

	@Test
	public void shouldCreateShowingsByCalendarAndNotExceedBounds() {
		LocalDateTime fromDate = LocalDateTime.of(2017, 4, 10, 20, 0);
		LocalDateTime untilDate = LocalDateTime.of(2017, 4, 14, 8, 0);
		CreateShowingsCommand command = prepareCreateShowingsCommandWithCalendar(fromDate, untilDate);
		//when
		List<Showing> showings = showingFactory.createShowings(command);
		//then
		assertThat(showings.size()).isEqualTo(2);
	}

	private CreateShowingsCommand prepareCreateShowingsCommandWithDate() {
		long cinemaId = 1L;
		long movieId = 2L;
		CreateShowingsCommand command = new CreateShowingsCommand();
		command.setCinemaId(cinemaId);
		command.setMovieId(movieId);
		command.setDates(localDateTimes);
		return command;
	}

	private CreateShowingsCommand prepareCreateShowingsCommandWithCalendar(LocalDateTime fromDate, LocalDateTime untilDate) {
		long cinemaId = 1L;
		long movieId = 2L;
		calendar.setFromDate(fromDate);
		calendar.setUntilDate(untilDate);
		Set<DayOfWeek> daysOfWeek = new HashSet<>();
		daysOfWeek.add(DayOfWeek.MONDAY);
		daysOfWeek.add(DayOfWeek.WEDNESDAY);
		daysOfWeek.add(DayOfWeek.FRIDAY);
		calendar.setDaysOfWeek(daysOfWeek);
		Set<LocalTime> times = new HashSet<>();
		times.add(LocalTime.of(14, 35));
		times.add(LocalTime.of(17, 50));
		calendar.setTimes(times);
		CreateShowingsCommand command = new CreateShowingsCommand();
		command.setCinemaId(cinemaId);
		command.setMovieId(movieId);
		command.setCalendar(calendar);
		return command;
	}

}