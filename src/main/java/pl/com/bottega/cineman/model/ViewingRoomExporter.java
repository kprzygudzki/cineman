package pl.com.bottega.cineman.model;

import java.util.List;

public interface ViewingRoomExporter {

	void addFreeSeats(List<Seat> freeSeats);

	void addOccupiedSeats(List<Seat> occupiedSeats);

}
