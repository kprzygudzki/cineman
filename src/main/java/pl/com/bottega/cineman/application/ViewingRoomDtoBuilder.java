package pl.com.bottega.cineman.application;

import pl.com.bottega.cineman.model.Seat;
import pl.com.bottega.cineman.model.ViewingRoomExporter;

import java.util.LinkedList;
import java.util.List;

public class ViewingRoomDtoBuilder implements ViewingRoomExporter {

	private ViewingRoomDto dto = new ViewingRoomDto();

	public ViewingRoomDto build() {
		ViewingRoomDto result = dto;
		dto = new ViewingRoomDto();
		return result;
	}

	@Override
	public void addFreeSeats(List<Seat> freeSeats) {
		List<Seat> seats = new LinkedList<>(freeSeats);
		dto.setFree(seats);
	}

	@Override
	public void addOccupiedSeats(List<Seat> occupiedSeats) {
		List<Seat> seats = new LinkedList<>(occupiedSeats);
		dto.setOccupied(seats);
	}

}
