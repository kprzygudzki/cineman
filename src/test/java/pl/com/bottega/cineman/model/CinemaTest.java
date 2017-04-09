package pl.com.bottega.cineman.model;

import org.junit.Test;
import pl.com.bottega.cineman.model.commands.CreateCinemaCommand;

import static org.assertj.core.api.Assertions.assertThat;

public class CinemaTest {

	private String city;
	private String name;

	@Test
	public void shouldRememberName() {
		//given
		name = "Felicity";
		//when
		Cinema cinema = createCinema();
		//then
		assertThat(cinema.getName()).isEqualTo(name);
	}

	@Test
	public void shouldRememberCity() {
		//given
		city = "Lublin";
		name = "Felicity";
		//when
		Cinema cinema = createCinema();
		//then
		assertThat(cinema.getName()).isEqualTo(name);
		assertThat(cinema.getCity()).isEqualTo(city);
	}

	private Cinema createCinema() {
		CreateCinemaCommand command = new CreateCinemaCommand();
		command.setName(name);
		command.setCity(city);
		return new Cinema(command);
	}

}
