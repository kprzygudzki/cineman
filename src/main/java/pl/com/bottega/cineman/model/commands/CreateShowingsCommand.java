package pl.com.bottega.cineman.model.commands;

import java.time.LocalDateTime;
import java.util.List;

public class CreateShowingsCommand implements Validatable {

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

	@Override
	public void validate(ValidationErrors errors) {
		if (cinemaId == null)
			errors.add("cinemaId", "is a required field and cannot be blank");
		if (movieId == null)
			errors.add("movieId", "is a required field and cannot be blank");
		if (dates == null && calendar == null) {
			errors.add("calendar", "either of calendar or dates is required; cannot both be blank");
			errors.add("dates", "either of calendar or dates is required; cannot both be blank");
		} else if (calendar != null && dates != null) {
			errors.add("calendar", "cannot provide both calendar and dates");
			errors.add("dates", "cannot provide both calendar and dates");
		} else if (dates != null && dates.isEmpty()) {
			errors.add("dates", "is a required field and cannot be blank");
		} else if (calendar != null) {
			calendar.validate(errors);
		}
	}

}
