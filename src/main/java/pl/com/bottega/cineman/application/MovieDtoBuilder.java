package pl.com.bottega.cineman.application;

import pl.com.bottega.cineman.model.MovieExporter;

import java.util.HashSet;
import java.util.Set;

public class MovieDtoBuilder implements MovieExporter {

	private MovieDto movieDto = new MovieDto();

	public MovieDto build() {
		MovieDto result = movieDto;
		movieDto = new MovieDto();
		return result;
	}

	@Override
	public void addId(Long id) {
		movieDto.setId(id);
	}

	@Override
	public void addTitle(String title) {
		movieDto.setTitle(title);
	}

	@Override
	public void addDescription(String description) {
		movieDto.setDescription(description);
	}

	@Override
	public void addActors(Set<String> actors) {
		movieDto.setActors(new HashSet<>(actors));
	}

	@Override
	public void addGenres(Set<String> genres) {
		movieDto.setGenres(new HashSet<>(genres));
	}

	@Override
	public void addMinAge(Integer minAge) {
		movieDto.setMinAge(minAge);
	}

	@Override
	public void addLength(Integer length) {
		movieDto.setLength(length);
	}

}
