package pl.com.bottega.cineman.model;

import pl.com.bottega.cineman.model.commands.CreateReservationCommand;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
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

	@OneToMany(cascade = CascadeType.ALL)
	private Set<Reservation> reservations = new HashSet<>();

	Showing(Cinema cinema, Movie movie, LocalDateTime beginsAt) {
		this.cinema = cinema;
		this.movie = movie;
		this.beginsAt = beginsAt;
	}

	public Showing() {
	}

	public ReservationNumber createReservation(CreateReservationCommand command) {
		Set<ReservationItem> reservationItems = command.getTickets();
		Set<Seat> seats = command.getSeats();
		Customer customer = command.getCustomer();
		Reservation reservation = new Reservation(seats, reservationItems, customer);
		reservations.add(reservation);
		return reservation.getReservationNumber();
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

	LocalDateTime getBeginsAt() {
		return beginsAt;
	}

	public ViewingRoom getViewingRoom() {
		return new ViewingRoom(reservations);
	}

	public void export(ShowingExporter exporter) {
		exporter.addId(id);
		exporter.addCinema(cinema);
		exporter.addMovie(movie);
		exporter.addBeginsAt(beginsAt);
	}

}
