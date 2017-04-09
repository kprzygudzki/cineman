package pl.com.bottega.cineman.model;

public interface MovieRepository {

	public void put(Movie movie);

	public Movie get(Long id);

}
