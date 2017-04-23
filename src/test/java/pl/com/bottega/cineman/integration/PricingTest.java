package pl.com.bottega.cineman.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cineman.application.AdminPanel;
import pl.com.bottega.cineman.application.MovieDto;
import pl.com.bottega.cineman.application.MovieDtoBuilder;
import pl.com.bottega.cineman.model.Movie;
import pl.com.bottega.cineman.model.MovieRepository;
import pl.com.bottega.cineman.model.PricingRepository;
import pl.com.bottega.cineman.model.commands.CreateMovieCommand;
import pl.com.bottega.cineman.model.commands.DefineMoviePricesCommand;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class PricingTest {

	@Autowired
	private AdminPanel adminPanel;

	@Autowired
	private PricingRepository pricingRepository;

	@Autowired
	private MovieRepository movieRepository;

	@Test
	public void shouldAddPricing() {
		CreateMovieCommand movieCommand = prepareCreateMovieCommand();
		Movie movie = new Movie(movieCommand);
		movieRepository.put(movie);
		MovieDto movieDto = prepareMovieDto(movie);

		Map<String, BigDecimal> prices = new HashMap<>();
		prices.put("student", new BigDecimal(17));
		prices.put("regular", new BigDecimal(22));
		DefineMoviePricesCommand pricesCommand = new DefineMoviePricesCommand();
		pricesCommand.setMovieId(movieDto.getId());
		pricesCommand.setPrices(prices);
		adminPanel.defineMoviePrices(pricesCommand);

		assertThat(pricingRepository.get(1L).getPrices()).isEqualTo(prices);
	}

	private CreateMovieCommand prepareCreateMovieCommand() {
		CreateMovieCommand command = new CreateMovieCommand();
		command.setTitle("Test title");
		command.setDescription("Desc");
		command.setActors(new HashSet<>());
		command.setGenres(new HashSet<>());
		command.setMinAge(12);
		command.setLength(120);
		return command;
	}

	private MovieDto prepareMovieDto(Movie movie) {
		MovieDtoBuilder builder = new MovieDtoBuilder();
		movie.export(builder);
		return builder.build();
	}

}
