package pl.com.bottega.cineman.application.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cineman.application.AdminPanel;
import pl.com.bottega.cineman.model.*;
import pl.com.bottega.cineman.model.commands.*;

import java.util.List;

import static pl.com.bottega.cineman.model.commands.Validatable.*;

@Transactional
public class StandardAdminPanel implements AdminPanel {

	private CinemaRepository cinemaRepository;
	private MovieRepository movieRepository;
	private ShowingRepository showingRepository;

	public StandardAdminPanel(
			CinemaRepository cinemaRepository, MovieRepository movieRepository, ShowingRepository showingRepository) {
		this.cinemaRepository = cinemaRepository;
		this.movieRepository = movieRepository;
		this.showingRepository = showingRepository;
	}

	@Override
	public void createCinema(CreateCinemaCommand command) {
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
		ValidationErrors errors = new ValidationErrors();
		command.validate(errors);
		if (!errors.isValid())
			throw new InvalidCommandException(errors);
		Cinema cinema = cinemaRepository.get(command.getCinemaId());
		Movie movie = movieRepository.get(command.getMovieId());
		ShowingFactory showingFactory = new ShowingFactory();
		List<Showing> showings = showingFactory.createShowings(command, cinema, movie);
		for (Showing showing : showings)
			showingRepository.put(showing);
	}

}
