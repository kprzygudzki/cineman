package pl.com.bottega.cineman.model;

import org.junit.Test;
import pl.com.bottega.cineman.application.MovieDto;
import pl.com.bottega.cineman.application.MovieDtoBuilder;
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
		CreateMovieCommand command = prepareCreateMovieCommand();
		//when
		Movie movie = new Movie(command);
		MovieDto movieDto = prepareMovieDto(movie);
		//then
		assertThat(movieDto.getTitle()).isEqualTo(title);
	}

	@Test
	public void shouldRememberDescription() {
		//given
		description = "You have to see this";
		CreateMovieCommand command = prepareCreateMovieCommand();
		//when
		Movie movie = new Movie(command);
		MovieDto movieDto = prepareMovieDto(movie);
		//then
		assertThat(movieDto.getDescription()).isEqualTo(description);
	}

	@Test
	public void shouldRememberActors() {
		//given
		actors = new HashSet<>();
		actors.add("Samuel L. Jackson");
		actors.add("Uma Thurman");
		CreateMovieCommand command = prepareCreateMovieCommand();
		//when
		Movie movie = new Movie(command);
		MovieDto movieDto = prepareMovieDto(movie);
		//then
		assertThat(movieDto.getActors()).isEqualTo(actors);
	}

	@Test
	public void shouldRememberGenres() {
		//given
		genres = new HashSet<>();
		genres.add("Crime");
		genres.add("Drama");
		CreateMovieCommand command = prepareCreateMovieCommand();
		//when
		Movie movie = new Movie(command);
		MovieDto movieDto = prepareMovieDto(movie);
		//then
		assertThat(movieDto.getGenres()).isEqualTo(genres);
	}

	@Test
	public void shouldRememberMinAge() {
		//given
		minAge = 16;
		CreateMovieCommand command = prepareCreateMovieCommand();
		//when
		Movie movie = new Movie(command);
		MovieDto movieDto = prepareMovieDto(movie);
		//then
		assertThat(movieDto.getMinAge()).isEqualTo(minAge);
	}

	@Test
	public void shouldRememberLength() {
		//given
		length = 178;
		CreateMovieCommand command = prepareCreateMovieCommand();
		//when
		Movie movie = new Movie(command);
		MovieDto movieDto = prepareMovieDto(movie);
		//then
		assertThat(movieDto.getLength()).isEqualTo(length);
	}

	private CreateMovieCommand prepareCreateMovieCommand() {
		CreateMovieCommand command = new CreateMovieCommand();
		command.setTitle(title);
		command.setDescription(description);
		command.setActors(actors);
		command.setGenres(genres);
		command.setMinAge(minAge);
		command.setLength(length);
		return command;
	}

	private MovieDto prepareMovieDto(Movie movie) {
		MovieDtoBuilder builder = new MovieDtoBuilder();
		movie.export(builder);
		return builder.build();
	}

}
