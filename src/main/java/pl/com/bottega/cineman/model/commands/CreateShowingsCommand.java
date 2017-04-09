package pl.com.bottega.cineman.model.commands;

import java.time.LocalDateTime;
import java.util.List;

public class CreateShowingsCommand {

	private Long cinemaId;
	private Long movieId;
	private List<LocalDateTime> dates;
	private Calendar calendar;

	public Long getCinemaId() {
		return cinemaId;
	}

	public void setCinemaId(Long cinemaId) {
		this.cinemaId = cinemaId;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public List<LocalDateTime> getDates() {
		return dates;
	}

	public void setDates(List<LocalDateTime> dates) {
		this.dates = dates;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

}
