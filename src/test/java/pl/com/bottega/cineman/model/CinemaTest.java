package pl.com.bottega.cineman.model;

import org.junit.Test;
import pl.com.bottega.cineman.application.CinemaDto;
import pl.com.bottega.cineman.application.CinemaDtoBuilder;
import pl.com.bottega.cineman.model.commands.CreateCinemaCommand;

import static org.assertj.core.api.Assertions.assertThat;

public class CinemaTest {

	private String city;
	private String name;

	@Test
	public void shouldRememberName() {
		//given
		name = "Felicity";
		CreateCinemaCommand command = prepareCreateCinemaCommand();
		//when
		Cinema cinema = new Cinema(command);
		CinemaDto cinemaDto = buildCinemaDto(cinema);
		//then
		assertThat(cinemaDto.getName()).isEqualTo(name);
	}

	@Test
	public void shouldRememberCity() {
		//given
		city = "Lublin";
		CreateCinemaCommand command = prepareCreateCinemaCommand();
		//when
		Cinema cinema = new Cinema(command);
		CinemaDto cinemaDto = buildCinemaDto(cinema);
		//then
		assertThat(cinemaDto.getCity()).isEqualTo(city);
	}

	private CreateCinemaCommand prepareCreateCinemaCommand() {
		CreateCinemaCommand command = new CreateCinemaCommand();
		command.setCity(city);
		command.setName(name);
		return command;
	}

	private CinemaDto buildCinemaDto(Cinema cinema) {
		CinemaDtoBuilder builder = new CinemaDtoBuilder();
		cinema.export(builder);
		return builder.build();
	}

}
