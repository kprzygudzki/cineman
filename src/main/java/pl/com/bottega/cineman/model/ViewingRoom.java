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

	ViewingRoom(Set<Reservation> reservations) {
		for (Reservation reservation : reservations) {
			Set<Seat> seats = reservation.getSeats();
			for (Seat seat : seats)
				this.seats[seat.getRow() - 1][seat.getNumber() - 1] = true;
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
		for (int zeroIndexedRowNumber = 0; zeroIndexedRowNumber < rowsCount; zeroIndexedRowNumber++)
			for (int zeroIndexedSeatNumber = 0; zeroIndexedSeatNumber < seatsCount; zeroIndexedSeatNumber++)
				if (seats[zeroIndexedRowNumber][zeroIndexedSeatNumber])
					occupiedSeats.add(new Seat(zeroIndexedRowNumber + 1, zeroIndexedSeatNumber + 1));
				else
					freeSeats.add(new Seat(zeroIndexedRowNumber + 1, zeroIndexedSeatNumber + 1));
	}

	public void export(ViewingRoomExporter exporter) {
		if (freeSeats == null || occupiedSeats == null)
			populateSeats();
		exporter.addFreeSeats(freeSeats);
		exporter.addOccupiedSeats(occupiedSeats);
	}

	public boolean isPossible(Set<Seat> seats) {
		return areNotOccupied(seats) && isAllowed(seats);
	}

	private boolean isAllowed(Set<Seat> seats) {
//		areNextToEachOther(seats);
//		areNotLeavingSingleEmptySeat(seats);
		return true;
	}

//	private boolean areNextToEachOther(Set<Seat> seatSet) {
//		List<Seat> seats = new LinkedList<>(seatSet);
//		int row = seats.get(0).getRow();
//		List<Integer> seatNumbers = new LinkedList<>();
//		for (Seat seat : seats) {
//			if (seat.getRow() != row)
//				return false;
//			seatNumbers.add(seat.getReservationNumber());
//		}
//		seatNumbers.sort(Integer::compareTo);
//		Integer i = seatNumbers.get(0);
//		for (Integer number : seatNumbers) {
//			if (number.compareTo(i) > 0)
//				return false;
//			i++;
//		}
//		return true;
//	}

	private boolean areNotOccupied(Set<Seat> seats) {
		return !getOccupiedSeats().containsAll(seats);
	}

}
