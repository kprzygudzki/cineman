package pl.com.bottega.cineman.model;

public interface CinemaRepository {

	void put(Cinema cinema);

	Cinema get(Long id);

	boolean existsWithCityAndName(String city, String name);

}
