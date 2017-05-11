package pl.com.bottega.cineman.application;

import pl.com.bottega.cineman.model.MovieExporter;

import static pl.com.bottega.cineman.application.ReservationDto.Movie;

public class ReservationMovieDtoBuilder implements MovieExporter {

	private Movie dto = new Movie();

	public Movie build() {
		Movie result = dto;
		dto = new Movie();
		return result;
	}

	@Override
	public void addId(Long id) {
		dto.setId(id);
	}

	@Override
	public void addTitle(String title) {
		dto.setTitle(title);
	}

}
