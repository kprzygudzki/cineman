package pl.com.bottega.cineman.model;

import javax.persistence.*;
import java.time.LocalDateTime;

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

	Showing(Cinema cinema, Movie movie, LocalDateTime beginsAt) {
		this.cinema = cinema;
		this.movie = movie;
		this.beginsAt = beginsAt;
	}

	public Showing() {
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

}
