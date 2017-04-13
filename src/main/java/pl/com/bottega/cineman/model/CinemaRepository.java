package pl.com.bottega.cineman.model;

public interface CinemaRepository {

	void put(Cinema cinema);

	Cinema get(Long id) throws CinemaNotFoundException;

	boolean existsWithCityAndName(String city, String name);

}
