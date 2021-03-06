package pl.com.bottega.cineman.application.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cineman.application.AdminPanel;
import pl.com.bottega.cineman.model.*;
import pl.com.bottega.cineman.model.commands.*;

import java.util.List;

@Transactional
public class StandardAdminPanel implements AdminPanel {

	private CinemaRepository cinemaRepository;
	private MovieRepository movieRepository;
	private ShowingRepository showingRepository;
	private PricingRepository pricingRepository;

	public StandardAdminPanel(CinemaRepository cinemaRepository, MovieRepository movieRepository,
							  ShowingRepository showingRepository, PricingRepository pricingRepository) {
		this.cinemaRepository = cinemaRepository;
		this.movieRepository = movieRepository;
		this.showingRepository = showingRepository;
		this.pricingRepository = pricingRepository;
	}

	@Override
	public void createCinema(CreateCinemaCommand command) {
		ensureNotADuplicate(command);
		Cinema cinema = new Cinema(command);
		cinemaRepository.put(cinema);
	}

	@Override
	public void createMovie(CreateMovieCommand command) {
		Movie movie = new Movie(command);
		movieRepository.put(movie);
	}

	@Override
	public void createShowings(CreateShowingsCommand command) {
		Cinema cinema = cinemaRepository.get(command.getCinemaId());
		Movie movie = movieRepository.get(command.getMovieId());
		ShowingFactory showingFactory = new ShowingFactory();
		List<Showing> showings = showingFactory.createShowings(command, cinema, movie);
		for (Showing showing : showings)
			showingRepository.put(showing);
	}

	@Override
	public void defineMoviePrices(DefineMoviePricesCommand command) {
		Movie movie = movieRepository.get(command.getMovieId());
		movie.definePricing(command);
		pricingRepository.put(movie.getPricing());
	}

	private void ensureNotADuplicate(CreateCinemaCommand command) {
		if (cinemaRepository.existsWithCityAndName(command.getCity(), command.getName()))
			throw new DuplicateCinemaException();
	}

}
