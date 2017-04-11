package pl.com.bottega.cineman.model.commands;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public class CreateShowingsCommand implements Validatable {

	private Long cinemaId;
	private Long movieId;
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm")
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

	@Override
	public void validate(ValidationErrors errors) {
		if (cinemaId == null)
			errors.add("cinemaId", "cannot be blank");
		if (movieId == null)
			errors.add("movieId", "cannot be blank");
		if ((dates == null || dates.isEmpty()) && calendar ==  null) {
			errors.add("calendar", "both calendar and dates cannot be blank");
			errors.add("dates", "both calendar and dates cannot be blank");
		}
		if (calendar == null)
			errors.add("calendar", "cannot be blank");
		else
			calendar.validate(errors);
	}

}
