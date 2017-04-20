package pl.com.bottega.cineman.acceptance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cineman.application.AdminPanel;
import pl.com.bottega.cineman.application.CinemaCatalog;
import pl.com.bottega.cineman.application.MovieCatalog;
import pl.com.bottega.cineman.application.PriceCatalog;

import pl.com.bottega.cineman.model.Money;
import pl.com.bottega.cineman.model.Movie;
import pl.com.bottega.cineman.model.commands.CreateCinemaCommand;
import pl.com.bottega.cineman.model.commands.CreateMovieCommand;
import pl.com.bottega.cineman.model.commands.CreatePriceCommand;
import pl.com.bottega.cineman.model.commands.DuplicateCinemaException;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class AdminPanelTest {

	@Autowired
	private AdminPanel adminPanel;

	@Autowired
	private CinemaCatalog cinemaCatalog;

	@Autowired
	private MovieCatalog movieCatalog;

	@Autowired
	private PriceCatalog priceCatalog;

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

	@Test(expected = DuplicateCinemaException.class)
	public void shouldNotAddTwoSameCinemas() {
		CreateCinemaCommand command = prepareCreateCinemaCommand();

		adminPanel.createCinema(command);
		adminPanel.createCinema(command);
	}

	@Test
	public void shouldAddTwoSameMovies() {
		CreateMovieCommand command = prepareCreateMovieCommand();

		adminPanel.createMovie(command);
		adminPanel.createMovie(command);
	}

	@Test
	public void shouldCreatePrices() {
		CreateMovieCommand command = prepareCreateMovieCommand();
		CreatePriceCommand priceCommand = new CreatePriceCommand();
		priceCommand.setMovie(new Movie(command));
		priceCommand.setRegular(new Money(20));
		priceCommand.setStudent(new Money(17));

		adminPanel.createPrices(priceCommand);
		assertThat(priceCatalog.getPrices().size()).isEqualTo(1);
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

}
