package pl.com.bottega.cineman.model;

import pl.com.bottega.cineman.model.commands.CreateReservationCommand;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ViewingRoom {

	private static final int rowsCount = 10;
	private static final int seatsCount = 15;

	private boolean[][] seats = new boolean[rowsCount][seatsCount];

	private List<Seat> freeSeats;
	private List<Seat> occupiedSeats;

	ViewingRoom(Set<Reservation> reservations) {
		for (Reservation reservation : reservations) {
			Set<Seat> seats = reservation.getSeats();
			for (Seat seat : seats)
				this.seats[seat.getRow()][seat.getNumber()] = true;
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
		for (int rowNumber = 0; rowNumber < rowsCount; rowNumber++)
			for (int seatNumber = 0; seatNumber < seatsCount; seatNumber++)
				if (seats[rowNumber][seatNumber])
					occupiedSeats.add(new Seat(rowNumber, seatNumber));
				else
					freeSeats.add(new Seat(rowNumber + 1, seatNumber + 1));
	}

	public void export(ViewingRoomExporter exporter) {
		if (freeSeats == null || occupiedSeats == null)
			populateSeats();
		exporter.addFreeSeats(freeSeats);
		exporter.addOccupiedSeats(occupiedSeats);
	}

	public void bookSeats(Set<Seat> seats) {
		if (isPossible(seats)) {
			for (Seat seat : seats)
				this.seats[seat.getRow()][seat.getNumber()] = true;
		}
	}

	private boolean isPossible(Set<Seat> seats) {
		for (Seat seat : seats) {
			if(getOccupiedSeats().contains(seat))
				return false;
		}
		return true;
	}
}
