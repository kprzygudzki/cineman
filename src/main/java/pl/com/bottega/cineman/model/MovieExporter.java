package pl.com.bottega.cineman.model;

import java.util.Set;

public interface MovieExporter {

	void addId(Long id);

	void addTitle(String title);

	void addDescription(String description);

	void addActors(Set<String> actors);

	void addGenres(Set<String> genres);

	void addMinAge(Integer minAge);

	void addLength(Integer length);

}
