package pl.com.bottega.cineman.model;

public interface PriceExporter {

	void addId(Long id);

	void addRegularPrice(Money regularPrice);

	void addStudentPrice(Money studentPrice);

	void addSchoolPrice(Money schoolPrice);

	void addChildrenPrice(Money childrenPrice);

	void addMovie(Movie movie);

}
