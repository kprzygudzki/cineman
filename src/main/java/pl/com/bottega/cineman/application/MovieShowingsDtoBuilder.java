package pl.com.bottega.cineman.application;

import pl.com.bottega.cineman.model.MovieExporter;

import java.util.Set;

public class MovieShowingsDtoBuilder implements MovieExporter {

	private MovieShowingsDto dto = new MovieShowingsDto();

	public MovieShowingsDtoBuilder() {
	}

	public MovieShowingsDto build() {
		MovieShowingsDto result = dto;
		dto = new MovieShowingsDto();
		return result;
	}

	@Override
	public void addId(Long id) {
	}

	@Override
	public void addTitle(String title) {
		dto.setTitle(title);
	}

	@Override
	public void addDescription(String description) {
		dto.setDescription(description);
	}

	@Override
	public void addActors(Set<String> actors) {
		dto.setActors(actors);
	}

	@Override
	public void addGenres(Set<String> genres) {
		dto.setGenres(genres);
	}

	@Override
	public void addMinAge(Integer minAge) {
		dto.setMinAge(minAge);
	}

	@Override
	public void addLength(Integer length) {
		dto.setLength(length);
	}

}
