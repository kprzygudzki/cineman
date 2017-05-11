package pl.com.bottega.cineman.model;

import java.time.LocalDateTime;

public interface ShowingExporter {

	void addId(Long id);

	default void addCinema(Cinema cinema) {
	}

	default void addMovie(Movie movie) {
	}

	void addBeginsAt(LocalDateTime beginsAt);

}
