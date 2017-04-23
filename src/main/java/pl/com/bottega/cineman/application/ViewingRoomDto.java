package pl.com.bottega.cineman.application;

import pl.com.bottega.cineman.model.Seat;

import java.util.List;

public class ViewingRoomDto {

//	{
//		"free": [
//		{"row": 1, "seat": 1}, {"row": 1, "seat": 2}, {"row": 1, "seat": 3}, ...
//    ],
//		"occupied": [
//		{"row": 2, "seat": 2}, {"row": 1, "seat": 3}, {"row": 1, "seat": 6}, ....
//    ]
//	}

	private List<Seat> free;
	private List<Seat> occupied;

	void setFree(List<Seat> free) {
		this.free = free;
	}

	void setOccupied(List<Seat> occupied) {
		this.occupied = occupied;
	}

	public List<Seat> getFree() {
		return free;
	}

	public List<Seat> getOccupied() {
		return occupied;
	}
}
