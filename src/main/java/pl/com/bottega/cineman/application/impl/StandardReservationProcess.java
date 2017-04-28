package pl.com.bottega.cineman.application.impl;

import pl.com.bottega.cineman.application.ReservationProcess;
import pl.com.bottega.cineman.application.ViewingRoomDto;
import pl.com.bottega.cineman.application.ViewingRoomDtoBuilder;
import pl.com.bottega.cineman.model.*;
import pl.com.bottega.cineman.model.commands.CalculatePriceCommand;
import pl.com.bottega.cineman.model.commands.InvalidCommandException;
import static pl.com.bottega.cineman.model.commands.Validatable.ValidationErrors;

public class StandardReservationProcess implements ReservationProcess {

	private PriceCalculator priceCalculator;
	private ShowingRepository showingRepo;

	public StandardReservationProcess(PriceCalculator priceCalculator, ShowingRepository showingRepository) {
		this.priceCalculator = priceCalculator;
		this.showingRepo = showingRepository;
	}

	@Override
	public CalculationResult calculatePrices(CalculatePriceCommand cmd) {
		ValidationErrors errors = new ValidationErrors();
		cmd.validate(errors);
		if(!errors.isValid())
			throw new InvalidCommandException(errors);
		return priceCalculator.calculatePrices(cmd);
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
