package pl.com.bottega.cineman.model;

import pl.com.bottega.cineman.infrastructure.CinemaNotFoundException;

public interface CinemaRepository {

	public void put(Cinema cinema);

	public Cinema get(Long id) throws CinemaNotFoundException;

}
