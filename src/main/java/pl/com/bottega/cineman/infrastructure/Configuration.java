package pl.com.bottega.cineman.infrastructure;

import org.springframework.context.annotation.Bean;
import pl.com.bottega.cineman.application.AdminPanel;
import pl.com.bottega.cineman.application.CinemaCatalog;
import pl.com.bottega.cineman.application.MovieCatalog;
import pl.com.bottega.cineman.application.impl.StandardAdminPanel;
import pl.com.bottega.cineman.model.CinemaRepository;
import pl.com.bottega.cineman.model.MovieRepository;
import pl.com.bottega.cineman.model.ShowingRepository;

@org.springframework.context.annotation.Configuration
public class Configuration {

	@Bean
	public CinemaRepository cinemaRepository() {
		return new JPACinemaRepository();
	}

	@Bean
	public MovieRepository movieRepository() {
		return new JPAMovieRepository();
	}

	@Bean
	public CinemaCatalog cinemaCatalog() {
		return new JPACinemaCatalog();
	}

	@Bean
	public MovieCatalog movieCatalog() {
		return new JPAMovieCatalog();
	}

	@Bean
	public AdminPanel adminPanel(
			CinemaRepository cinemaRepository, MovieRepository movieRepository, ShowingRepository showingRepository) {
		return new StandardAdminPanel(cinemaRepository, movieRepository, showingRepository);
	}

	@Bean
	public ShowingRepository showingRepository() {
		return new JPAShowingRepository();
	}

}
