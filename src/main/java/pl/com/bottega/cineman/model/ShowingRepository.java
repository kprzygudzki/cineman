package pl.com.bottega.cineman.model;

public interface ShowingRepository {

	void put(Showing showing);

	Showing get(Long showingId);

}
