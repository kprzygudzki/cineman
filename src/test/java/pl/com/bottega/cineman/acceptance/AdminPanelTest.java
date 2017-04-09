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
		CreateCinemaCommand cmd = new CreateCinemaCommand();
		cmd.setName("Felicity");
		cmd.setCity("Lublin");

		adminPanel.createCinema(cmd);

		assertThat(cinemaCatalog.getCinemas().size()).isEqualTo(1);
	}

	@Test
	public void shouldCreateMovie() {
		CreateMovieCommand cmd = new CreateMovieCommand();
		Set<String> actors = new HashSet<>();
		actors.add("John Travolta");
		actors.add("Samuel L. Jackson");
		actors.add("Uma Thurman");
		Set<String> genres = new HashSet<>();
		genres.add("Crime");
		genres.add("Drama");
		cmd.setTitle("Pulp Fiction");
		cmd.setDescription("You have to see this");
		cmd.setActors(actors);
		cmd.setGenres(genres);
		cmd.setMinAge(16);
		cmd.setLength(120);

		adminPanel.createMovie(cmd);
		assertThat(movieCatalog.getMovies().size()).isEqualTo(1);
	}

}
