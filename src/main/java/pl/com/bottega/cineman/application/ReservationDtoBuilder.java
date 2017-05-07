package pl.com.bottega.cineman.application;

import pl.com.bottega.cineman.model.*;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
		dto.setSeats(new LinkedList<>(seats));
	}

	@Override
	public void addItemsAndShowing(Set<ReservationItem> items, Showing showing) {
		addShowing(showing);
		addMovie(showing);
		addTicketsAndPrices(items, showing);
	}

	private void addMovie(Showing showing) {
		ReservationDto.Movie movie = dto.new Movie();
		movie.setId(showing.getMovie().getId());
		movie.setTitle(showing.getMovie().getTitle());
		dto.setMovie(movie);
	}

	private void addTicketsAndPrices(Set<ReservationItem> items, Showing showing) {
		List<ReservationDto.Ticket> tickets = createTickets(items, showing);
		dto.setTickets(tickets);
		dto.setTotalPrice(calculateTotalOrderPrice(tickets));
	}

	private BigDecimal calculateTotalOrderPrice(List<ReservationDto.Ticket> tickets) {
		BigDecimal totalPrice = BigDecimal.ZERO;
		for (ReservationDto.Ticket ticket : tickets)
			totalPrice = totalPrice.add(ticket.getTotalPrice());
		return totalPrice;
	}

	private List<ReservationDto.Ticket> createTickets(Set<ReservationItem> items, Showing showing) {
		List<ReservationDto.Ticket> tickets = new LinkedList<>();
		Pricing pricing = showing.getMovie().getPricing();
		if (pricing == null)
			throw new ResourceNotFoundException("pricing for movie", showing.getMovie().getId());
		Map<String, BigDecimal> prices = pricing.getPrices();
		for (ReservationItem item : items) {
			ReservationDto.Ticket ticket = createTicket(item, prices);
			tickets.add(ticket);
		}
		return tickets;
	}

	private ReservationDto.Ticket createTicket(ReservationItem item, Map<String, BigDecimal> prices) {
		Integer count = item.getCount();
		String kind = item.getKind();
		BigDecimal unitTicketPrice = prices.get(kind);
		BigDecimal totalTicketPrice = unitTicketPrice.multiply(BigDecimal.valueOf(count));

		ReservationDto.Ticket ticket = dto.new Ticket();
		ticket.setCount(count);
		ticket.setKind(kind);
		ticket.setUnitPrice(unitTicketPrice);
		ticket.setTotalPrice(totalTicketPrice);
		return ticket;
	}

	private void addShowing(Showing showing) {
		ReservationDto.Show show = dto.new Show();
		show.setId(showing.getId());
		show.setTime(showing.getBeginsAt());
		dto.setShow(show);
	}

	@Override
	public void addCustomer(Customer customer) {
		dto.setCustomer(customer);
	}

}
