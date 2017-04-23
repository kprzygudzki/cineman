package pl.com.bottega.cineman.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ViewingRoom {

	private static final int rowsCount = 10;
	private static final int seatsCount = 15;

	private boolean[][] seats = new boolean[rowsCount][seatsCount];

	private List<Seat> freeSeats;
	private List<Seat> occupiedSeats;

	public ViewingRoom(Set<Reservation> reservations) {
		for (Reservation reservation : reservations) {
			Set<Seat> seats = reservation.getSeats();
			for (Seat seat : seats) {
				this.seats[seat.getRow()][seat.getNumber()] = true;
			}
		}
	}

	public List<Seat> getFreeSeats() {
		if (freeSeats == null)
			populateSeats();
		return freeSeats;
	}

	public List<Seat> getOccupiedSeats() {
		if (occupiedSeats == null)
			populateSeats();
		return occupiedSeats;
	}

	private void populateSeats() {
		freeSeats = new LinkedList<>();
		occupiedSeats = new LinkedList<>();
		for (int rowNumber = 1; rowNumber <= rowsCount; rowNumber++)
			for (int seatNumber = 1; seatNumber <= seatsCount; seatNumber++)
				if (seats[rowNumber][seatNumber])
					freeSeats.add(new Seat(rowNumber, seatNumber));
				else
					occupiedSeats.add(new Seat(rowNumber, seatNumber));
	}

	public void export(ViewingRoomExporter exporter) {
		if (freeSeats == null || occupiedSeats == null)
			populateSeats();
		exporter.addFreeSeats(freeSeats);
		exporter.addOccupiedSeats(occupiedSeats);
	}

}
