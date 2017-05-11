package pl.com.bottega.cineman.application;

import com.fasterxml.jackson.annotation.JsonFormat;
import pl.com.bottega.cineman.model.CalculationItem;
import pl.com.bottega.cineman.model.Customer;
import pl.com.bottega.cineman.model.Seat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class ReservationDto {

	private String number;
	private Show show;
	private Movie movie;
	private Set<CalculationItem> tickets;
	private Set<Seat> seats;
	private Customer customer;
	private String status;
	private BigDecimal totalPrice;

	public static class Show {

		private Long Id;

		@JsonFormat(pattern = "yyyy/MM/dd HH:mm")
		private LocalDateTime time;

		public Long getId() {
			return Id;
		}

		public void setId(Long id) {
			Id = id;
		}

		public LocalDateTime getTime() {
			return time;
		}

		public void setTime(LocalDateTime time) {
			this.time = time;
		}

	}

	public static class Movie {

		private Long id;
		private String title;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Set<CalculationItem> getTickets() {
		return tickets;
	}

	public void setTickets(Set<CalculationItem> tickets) {
		this.tickets = tickets;
	}

	public Set<Seat> getSeats() {
		return seats;
	}

	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

}
