package pl.com.bottega.cineman.application.impl;

import pl.com.bottega.cineman.application.ReservationProcess;
import pl.com.bottega.cineman.application.ViewingRoomDto;
import pl.com.bottega.cineman.application.ViewingRoomDtoBuilder;
import pl.com.bottega.cineman.model.ReservationNumber;
import pl.com.bottega.cineman.model.Showing;
import pl.com.bottega.cineman.model.ShowingRepository;
import pl.com.bottega.cineman.model.ViewingRoom;
import pl.com.bottega.cineman.model.commands.CreateReservationCommand;

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

	@Override
	public ReservationNumber create(CreateReservationCommand command) {
		Showing showing = showingRepo.get(command.getShowingId());
		ReservationNumber reservationNumber = showing.createReservation(command);
		showingRepo.put(showing);
		return reservationNumber;
	}

	private ViewingRoomDto createViewingRoomDto(ViewingRoom viewingRoom) {
		ViewingRoomDtoBuilder builder = new ViewingRoomDtoBuilder();
		viewingRoom.export(builder);
		return builder.build();
	}

}
