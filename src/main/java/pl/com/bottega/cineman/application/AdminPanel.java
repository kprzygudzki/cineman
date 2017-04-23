package pl.com.bottega.cineman.application;

import pl.com.bottega.cineman.model.commands.CreateCinemaCommand;
import pl.com.bottega.cineman.model.commands.CreateMovieCommand;
import pl.com.bottega.cineman.model.commands.CreateShowingsCommand;
import pl.com.bottega.cineman.model.commands.DefineMoviePricesCommand;

public interface AdminPanel {

    void createCinema(CreateCinemaCommand command);

    void createMovie(CreateMovieCommand command);

    void createShowings(CreateShowingsCommand command);

    void defineMoviePrices(DefineMoviePricesCommand command);
}
