package pl.com.bottega.cineman.acceptance;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cineman.application.AdminPanel;
import pl.com.bottega.cineman.application.CinemaCatalog;
import pl.com.bottega.cineman.application.MovieCatalog;
import pl.com.bottega.cineman.model.commands.CreateCinemaCommand;
import pl.com.bottega.cineman.model.commands.CreateMovieCommand;
import pl.com.bottega.cineman.model.commands.DuplicateRecordException;

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

	@Test
	public void shouldCreateCinema() {
		CreateCinemaCommand command = new CreateCinemaCommand();
		command.setName("Felicity");
		command.setCity("Lublin");

		adminPanel.createCinema(command);

		assertThat(cinemaCatalog.getCinemas().size()).isEqualTo(1);
	}

	@Test
	public void shouldCreateMovie() {
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

		adminPanel.createMovie(command);
		assertThat(movieCatalog.getMovies().size()).isEqualTo(1);
	}

	@Test(expected = DuplicateRecordException.class)
	public void shouldNotAddTwoSameCinemas() {
		CreateCinemaCommand command = new CreateCinemaCommand();
		command.setName("Felicity");
		command.setCity("Lublin");

		adminPanel.createCinema(command);
		adminPanel.createCinema(command);
	}

}
