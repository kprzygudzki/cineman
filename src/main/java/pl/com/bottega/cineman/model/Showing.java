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

	public Showing(Cinema cinema, Movie movie, LocalDateTime beginsAt) {
		this.cinema = cinema;
		this.movie = movie;
		this.beginsAt = beginsAt;
	}

	public Showing() {
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

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public LocalDateTime getBeginsAt() {
		return beginsAt;
	}

	public void setBeginsAt(LocalDateTime beginsAt) {
		this.beginsAt = beginsAt;
	}

}
