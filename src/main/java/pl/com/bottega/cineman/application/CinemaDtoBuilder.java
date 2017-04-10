package pl.com.bottega.cineman.application;

import pl.com.bottega.cineman.model.CinemaExporter;

public class CinemaDtoBuilder implements CinemaExporter {

	private CinemaDto cinemaDto = new CinemaDto();

	@Override
	public void addId(Long id) {
		cinemaDto.setId(id);
	}

	@Override
	public void addName(String name) {
		cinemaDto.setName(name);
	}

	@Override
	public void addCity(String city) {
		cinemaDto.setCity(city);
	}

	public CinemaDto build() {
		return cinemaDto;
	}

}
