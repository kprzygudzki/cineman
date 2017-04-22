package pl.com.bottega.cineman.model;

import java.time.LocalDateTime;

public interface ShowingExporter {

	void addId(Long id);

	void addCinema(Cinema cinema);

	void addMovie(Movie movie);

	void addBeginsAt(LocalDateTime beginsAt);

}
