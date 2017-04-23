package pl.com.bottega.cineman.model;

public interface MovieRepository {

	void put(Movie movie);

	Movie get(Long id);

}
