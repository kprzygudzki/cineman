package pl.com.bottega.cineman.model;

public interface CinemaRepository {

	public void put(Cinema cinema);

	public Cinema get(Long id) throws CinemaNotFoundException;

}
