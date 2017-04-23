package pl.com.bottega.cineman.application.impl;

import pl.com.bottega.cineman.application.ReservationProcess;
import pl.com.bottega.cineman.application.ViewingRoomDto;
import pl.com.bottega.cineman.application.ViewingRoomDtoBuilder;
import pl.com.bottega.cineman.model.Showing;
import pl.com.bottega.cineman.model.ShowingRepository;
import pl.com.bottega.cineman.model.ViewingRoom;

public class StandardReservationProcess implements ReservationProcess {

	private ShowingRepository showingRepo;

	public StandardReservationProcess(ShowingRepository showingRepository) {
		this.showingRepo = showingRepository;
	}

	@Override
	public ViewingRoomDto getSeats(Long showingId) {
		Showing showing = showingRepo.get(showingId);
		ViewingRoom viewingRoom = showing.getViewingRoom();
		return createViewingRoomDto(viewingRoom);
	}

	private ViewingRoomDto createViewingRoomDto(ViewingRoom viewingRoom) {
		ViewingRoomDtoBuilder builder = new ViewingRoomDtoBuilder();
		viewingRoom.export(builder);
		return builder.build();
	}

}