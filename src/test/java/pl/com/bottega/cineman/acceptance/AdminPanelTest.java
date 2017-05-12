package pl.com.bottega.cineman.acceptance;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.com.bottega.cineman.application.AdminPanel;
import pl.com.bottega.cineman.application.CinemaCatalog;
import pl.com.bottega.cineman.application.MovieCatalog;
import pl.com.bottega.cineman.application.MovieShowingsDto;
import pl.com.bottega.cineman.helper.DbCleaner;
import pl.com.bottega.cineman.model.DuplicateCinemaException;
import pl.com.bottega.cineman.model.commands.CreateCinemaCommand;
import pl.com.bottega.cineman.model.commands.CreateMovieCommand;
import pl.com.bottega.cineman.model.commands.CreateShowingsCommand;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AdminPanelTest {

	@Autowired
	private AdminPanel adminPanel;

	@Autowired
	private CinemaCatalog cinemaCatalog;

	@Autowired
	private MovieCatalog movieCatalog;

	@Autowired
	private DbCleaner cleaner;

	@Before
	public void setUp() throws Exception {
		cleaner.clean();
	}

	@Test
	public void shouldCreateCinema() {
		CreateCinemaCommand command = prepareCreateCinemaCommand();
		adminPanel.createCinema(command);
		assertThat(cinemaCatalog.getCinemas().size()).isEqualTo(1);
	}

	@Test
	public void shouldCreateMovie() {
		CreateMovieCommand command = prepareCreateMovieCommand();
		adminPanel.createMovie(command);
		assertThat(movieCatalog.getMovies().size()).isEqualTo(1);
	}

	@Test
	public void shouldNotAddTwoSameCinemas() {
		CreateCinemaCommand command = prepareCreateCinemaCommand();
		adminPanel.createCinema(command);
		Throwable thrown = catchThrowable(() -> adminPanel.createCinema(command));
		assertThat(thrown).isInstanceOf(DuplicateCinemaException.class);
	}

	@Test
	public void shouldAddTwoSameMovies() {
		CreateMovieCommand command = prepareCreateMovieCommand();
		adminPanel.createMovie(command);
		adminPanel.createMovie(command);
	}

	@Test
	public void shouldCreateSingleShowing() {
		Long cinemaId = addCinema();
		List<LocalDateTime> dates = new LinkedList<>();
		LocalDate date = LocalDate.of(2017, 7, 15);
		dates.add(date.atTime(LocalTime.of(17, 30)));
		CreateShowingsCommand command = prepareSingleShowingCommand(cinemaId, addMovie(), dates);
		adminPanel.createShowings(command);
		List<MovieShowingsDto> showings = cinemaCatalog.getShowings(cinemaId, date);
		assertThat(showings.get(0).getShows().size()).isEqualTo(1);
		assertThat(showings.size()).isEqualTo(1);
	}

	@Test
	public void shouldAddSingularShowings() {
		Long cinemaId = addCinema();
		List<LocalDateTime> dates = new LinkedList<>();
		LocalDate date = LocalDate.of(2017, 7, 15);
		dates.add(date.atTime(LocalTime.of(17, 30)));
		dates.add(date.atTime(LocalTime.of(20, 30)));
		CreateShowingsCommand command = prepareSingleShowingCommand(cinemaId, addMovie(), dates);
		adminPanel.createShowings(command);
		List<MovieShowingsDto> showings = cinemaCatalog.getShowings(cinemaId, date);
		assertThat(showings.size()).isEqualTo(1);
		assertThat(showings.get(0).getShows().size()).isEqualTo(2);

		System.out.println();
	}

	private Long addMovie() {
		adminPanel.createMovie(prepareCreateMovieCommand());
		return movieCatalog.getMovies().get(0).getId();
	}

	private Long addCinema() {
		adminPanel.createCinema(prepareCreateCinemaCommand());
		return cinemaCatalog.getCinemas().get(0).getId();
	}

	private CreateCinemaCommand prepareCreateCinemaCommand() {
		CreateCinemaCommand command = new CreateCinemaCommand();
		command.setName("Felicity");
		command.setCity("Lublin");
		return command;
	}

	private CreateMovieCommand prepareCreateMovieCommand() {
		CreateMovieCommand command = new CreateMovieCommand();

		Set<String> actors = new HashSet<>();
		actors.add("John Travolta");
		actors.add("Samuel L. Jackson");
		actors.add("Uma Thurman");

		Set<String> genres = new HashSet<>();
		genres.add("Crime");
		genres.add("Drama");

		command.setTitle("Pulp Fiction");
		command.setDescription("You have to see this");
		command.setActors(actors);
		command.setGenres(genres);
		command.setMinAge(16);
		command.setLength(120);

		return command;
	}

	private CreateShowingsCommand prepareSingleShowingCommand(Long cinemaId, Long movieId, List<LocalDateTime> dates) {
		CreateShowingsCommand command = new CreateShowingsCommand();
		command.setCinemaId(cinemaId);
		command.setMovieId(movieId);
		command.setDates(dates);
		return command;
	}

}
