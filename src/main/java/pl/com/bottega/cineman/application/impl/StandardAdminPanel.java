package pl.com.bottega.cineman.application.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cineman.application.AdminPanel;
import pl.com.bottega.cineman.model.Cinema;
import pl.com.bottega.cineman.model.CinemaRepository;
import pl.com.bottega.cineman.model.Movie;
import pl.com.bottega.cineman.model.MovieRepository;
import pl.com.bottega.cineman.model.commands.CreateCinemaCommand;
import pl.com.bottega.cineman.model.commands.CreateMovieCommand;
import pl.com.bottega.cineman.model.commands.CreateShowingsCommand;

@Transactional
public class StandardAdminPanel implements AdminPanel {

	private CinemaRepository cinemaRepository;
	private MovieRepository movieRepository;

	public StandardAdminPanel(CinemaRepository cinemaRepository, MovieRepository movieRepository) {
		this.cinemaRepository = cinemaRepository;
		this.movieRepository = movieRepository;
	}

	@Override
	public void createCinema(CreateCinemaCommand cmd) {
		Cinema cinema = new Cinema(cmd);
		cinemaRepository.put(cinema);
	}

	@Override
	public void createMovie(CreateMovieCommand cmd) {
		Movie movie = new Movie(cmd);
		movieRepository.put(movie);
	}

	@Override
	public void createShowings(CreateShowingsCommand cmd) {

	}
}
