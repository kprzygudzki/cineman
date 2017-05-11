package pl.com.bottega.cineman.model;

import pl.com.bottega.cineman.application.InvalidRequestException;
import pl.com.bottega.cineman.model.commands.CreateReservationCommand;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
public class Showing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Cinema cinema;

	@ManyToOne
	private Movie movie;

	private LocalDateTime beginsAt;
	private LocalDateTime endsAt;

	@OneToMany(mappedBy = "showing", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Reservation> reservations = new HashSet<>();

	Showing(Cinema cinema, Movie movie, LocalDateTime beginsAt) {
		this.cinema = cinema;
		this.movie = movie;
		this.beginsAt = beginsAt;
		this.endsAt = beginsAt.plusMinutes(movie.getLength());
	}

	public Showing() {
	}

	public ReservationNumber createReservation(CreateReservationCommand command) {
		Set<Seat> seats = command.getSeats();
		ensureLegalTicketKinds(command);
		getViewingRoom().ensureLegal(seats);
		Reservation reservation = new Reservation(this, command);
		reservations.add(reservation);
		return reservation.getNumber();
	}

	private void ensureLegalTicketKinds(CreateReservationCommand command) {
		Set<ReservationItem> tickets = command.getTickets();
		Map<String, BigDecimal> pricing = movie.getPricing().getPrices();
		for (ReservationItem ticket : tickets) {
			String kind = ticket.getKind();
			if (!pricing.containsKey(kind))
				throw new InvalidRequestException(String.format("%s is not a valid kind of ticket", kind));
		}
	}

	public ViewingRoom getViewingRoom() {
		return new ViewingRoom(reservations);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public Movie getMovie() {
		return movie;
	}

	public LocalDateTime getBeginsAt() {
		return beginsAt;
	}

	public void export(ShowingExporter exporter) {
		exporter.addId(id);
		exporter.addCinema(cinema);
		exporter.addMovie(movie);
		exporter.addBeginsAt(beginsAt);
	}

	public Pricing getPricing() {
		Pricing pricing = movie.getPricing();
		if (pricing == null)
			throw new ResourceNotFoundException("pricing for movie", movie.getId());
		return pricing;
	}

}
