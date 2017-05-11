package pl.com.bottega.cineman.model;

import java.util.Set;

public interface MovieExporter {

	void addId(Long id);

	void addTitle(String title);

	default void addDescription(String description) {
	}

	default void addActors(Set<String> actors) {
	}

	default void addGenres(Set<String> genres) {
	}

	default void addMinAge(Integer minAge) {
	}

	default void addLength(Integer length) {
	}

}
