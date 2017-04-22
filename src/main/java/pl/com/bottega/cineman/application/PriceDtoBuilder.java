package pl.com.bottega.cineman.application;

import pl.com.bottega.cineman.model.Money;
import pl.com.bottega.cineman.model.Movie;
import pl.com.bottega.cineman.model.PriceExporter;

public class PriceDtoBuilder implements PriceExporter {

	private PriceDto priceDto = new PriceDto();

	@Override
	public void addId(Long id) {
		priceDto.setId(id);
	}

	@Override
	public void addRegularPrice(Money regularPrice) {
		priceDto.setRegular(regularPrice);
	}

	@Override
	public void addStudentPrice(Money studentPrice) {
		priceDto.setStudent(studentPrice);
	}

	@Override
	public void addSchoolPrice(Money schoolPrice) {
		priceDto.setSchool(schoolPrice);
	}

	@Override
	public void addChildrenPrice(Money childrenPrice) {
		priceDto.setChildren(childrenPrice);
	}

	@Override
	public void addMovie(Movie movie) {
		priceDto.setMovie(movie);
	}

	public PriceDto build() {
		PriceDto result = priceDto;
		priceDto = new PriceDto();
		return result;
	}
}
