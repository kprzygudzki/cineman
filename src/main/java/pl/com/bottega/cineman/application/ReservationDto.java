package pl.com.bottega.cineman.application;

import com.fasterxml.jackson.annotation.JsonFormat;
import pl.com.bottega.cineman.model.Customer;
import pl.com.bottega.cineman.model.Seat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ReservationDto {

	private String number;
	private Show show;
	private Movie movie;
	private List<Ticket> tickets;
	private List<Seat> seats;
	private Customer customer;
	private String status;
	private BigDecimal totalPrice;

	class Show {

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

	class Movie {

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

	class Ticket {

		private String kind;
		private Integer count;
		private BigDecimal unitPrice;
		private BigDecimal totalPrice;

		public String getKind() {
			return kind;
		}

		public void setKind(String kind) {
			this.kind = kind;
		}

		public Integer getCount() {
			return count;
		}

		public void setCount(Integer count) {
			this.count = count;
		}

		public BigDecimal getUnitPrice() {
			return unitPrice;
		}

		public void setUnitPrice(BigDecimal unitPrice) {
			this.unitPrice = unitPrice;
		}

		public BigDecimal getTotalPrice() {
			return totalPrice;
		}

		public void setTotalPrice(BigDecimal totalPrice) {
			this.totalPrice = totalPrice;
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

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
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
