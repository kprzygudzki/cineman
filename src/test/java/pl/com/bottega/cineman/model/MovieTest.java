package pl.com.bottega.cineman.model;

import org.junit.Test;
import pl.com.bottega.cineman.model.commands.CreateMovieCommand;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class MovieTest {

	private String title;
	private String description;
	private Set<String> actors;
	private Set<String> genres;
	private Integer minAge;
	private Integer length;

	@Test
	public void shouldRememberTitle() {
		//given
		title = "Pulp Fiction";
		//when
		Movie movie = createMovie();
		//then
		assertThat(movie.getTitle()).isEqualTo(title);
	}

	@Test
	public void shouldRememberDescription() {
		//given
		description = "You have to see this";
		//when
		Movie movie = createMovie();
		//then
		assertThat(movie.getDescription()).isEqualTo(description);
	}

	@Test
	public void shouldRememberActors() {
		//given
		actors = new HashSet<>();
		actors.add("Samuel L. Jackson");
		actors.add("Uma Thurman");
		//when
		Movie movie = createMovie();
		//then
		assertThat(movie.getActors()).isEqualTo(actors);
	}

	@Test
	public void shouldRememberGenres() {
		//given
		genres = new HashSet<>();
		genres.add("Crime");
		genres.add("Drama");
		//when
		Movie movie = createMovie();
		//then
		assertThat(movie.getGenres()).isEqualTo(genres);
	}

	@Test
	public void shouldRememberMinAge() {
		//given
		minAge = 16;
		//when
		Movie movie = createMovie();
		//then
		assertThat(movie.getMinAge()).isEqualTo(minAge);
	}

	@Test
	public void shouldRememberLength() {
		//given
		length = 178;
		//when
		Movie movie = createMovie();
		//then
		assertThat(movie.getLength()).isEqualTo(length);
	}

	private Movie createMovie() {
		CreateMovieCommand command = new CreateMovieCommand();
		command.setTitle(title);
		command.setDescription(description);
		command.setActors(actors);
		command.setGenres(genres);
		command.setMinAge(minAge);
		command.setLength(length);
		return new Movie(command);
	}

}
