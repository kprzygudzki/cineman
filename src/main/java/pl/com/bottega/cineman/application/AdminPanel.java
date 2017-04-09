package pl.com.bottega.cineman.application;

import pl.com.bottega.cineman.model.commands.CreateCinemaCommand;
import pl.com.bottega.cineman.model.commands.CreateMovieCommand;
import pl.com.bottega.cineman.model.commands.CreateShowingsCommand;

public interface AdminPanel {

    void createCinema(CreateCinemaCommand cmd);

    void createMovie(CreateMovieCommand cmd);

    void createShowings(CreateShowingsCommand cmd);

}
