package pl.com.bottega.cineman.application;

import pl.com.bottega.cineman.model.*;

import java.util.Set;

public class ReservationDtoBuilder implements ReservationExporter {

	private ReservationDto dto = new ReservationDto();

	public ReservationDto build() {
		ReservationDto result = dto;
		dto = new ReservationDto();
		return result;
	}

	@Override
	public void addNumber(ReservationNumber number) {
		 dto.setNumber(number.getReservationNumber());
	}

	@Override
	public void addStatus(ReservationStatus status) {
		dto.setStatus(status.toString());
	}

	@Override
	public void addSeats(Set<Seat> seats) {
		dto.setSeats(seats);
	}

	@Override
	public void addCalculationResult(CalculationResult calculationResult) {
		dto.setTickets(calculationResult.getCalculationItems());
		dto.setTotalPrice(calculationResult.getTotalPrice());
	}

	@Override
	public void addCustomer(Customer customer) {
		dto.setCustomer(customer);
	}

	@Override
	public void addShowing(Showing showing) {
		dto.setShow(getShowDto(showing));
		dto.setMovie(getMovie(showing));
	}

	private ReservationDto.Movie getMovie(Showing showing) {
		ReservationMovieDtoBuilder builder = new ReservationMovieDtoBuilder();
		showing.getMovie().export(builder);
		return builder.build();
	}

	private ReservationDto.Show getShowDto(Showing showing) {
		ReservationShowDtoBuilder builder = new ReservationShowDtoBuilder();
		showing.export(builder);
		return builder.build();
	}

}
