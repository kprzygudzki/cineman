package pl.com.bottega.cineman.model;

import java.util.HashSet;
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
				this.seats[seat.getRow() - 1][seat.getSeat() - 1] = true;
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

	public void ensureLegal(Set<Seat> seats) {
		ensureNotOccupied(seats);
		ensureAllowed(seats);
	}

	private void ensureNotOccupied(Set<Seat> seats) {
		Set<Seat> unavailableSeats = new HashSet<>(getOccupiedSeats());
		unavailableSeats.retainAll(seats);
		if (!unavailableSeats.isEmpty())
			throw new IllegalSeatingException("Requested seats are not available", unavailableSeats);

	}

	private void ensureAllowed(Set<Seat> seats) {
		boolean consecutive = areConsecutive(seats);
		boolean sameRow = areInTheSameRow(seats);
		boolean possible = isPossible(seats.size());

		if (!sameRow && possible)
			throw new IllegalSeatingException("Requested seats are not in the same row", seats);
		if (!consecutive && possible)
			throw new IllegalSeatingException("Requested seats are not consecutive", seats);
	}

	private boolean areInTheSameRow(Set<Seat> seats) {
		Integer row = null;
		for (Seat seat : seats) {
			if (row == null)
				row = seat.getRow();
			if (!row.equals(seat.getRow()))
				return false;
		}
		return true;
	}

	private boolean areConsecutive(Set<Seat> seats) {
		List<Integer> seatNumbers = new LinkedList<>();
		for (Seat seat : seats)
			seatNumbers.add(seat.getSeat());
		seatNumbers.sort(Integer::compareTo);

		Integer temporaryNumber = seatNumbers.get(0);
		for (int i = 0; i < seatNumbers.size(); i++, temporaryNumber++)
			if (!seatNumbers.get(i).equals(temporaryNumber))
				return false;
		return true;
	}

	private boolean isPossible(int requiredSeats) {
		int foundSeats;
		for (int row = 0; row < rowsCount; row++) {
			foundSeats = 0;
			for (int seat = 0; seat < seatsCount - requiredSeats; seat++) {
				if (!seats[row][seat]) {
					if (++foundSeats == requiredSeats)
						if (!leavesOrphanedSeat(row, seat))
							return true;
				} else
					foundSeats = 0;
			}
		}
		return false;
	}

	private boolean leavesOrphanedSeat(int row, int seat) {
		if (seat == seatsCount - 1)
			return false;
		if (seat + 1 == seatsCount - 1)
			return (seats[row][seat + 1]);
		return (!seats[row][seat + 1]) && (seats[row][seat + 2]);
	}

}
