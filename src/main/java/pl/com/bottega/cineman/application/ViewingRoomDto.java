package pl.com.bottega.cineman.application;

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

	private List<SeatDto> free;
	private List<SeatDto> occupied;

	public void setFree(List<SeatDto> free) {
		this.free = free;
	}

	public void setOccupied(List<SeatDto> occupied) {
		this.occupied = occupied;
	}

}
