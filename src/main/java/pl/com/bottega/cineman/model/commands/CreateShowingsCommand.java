package pl.com.bottega.cineman.model.commands;

import java.time.LocalDateTime;
import java.util.List;

public class CreateShowingsCommand implements Validatable {

	private Long cinemaId;
	private Long movieId;
	private List<LocalDateTime> dates;
	private Calendar calendar;

	private static final String REQUIRED_FIELD = "is a required field and cannot be blank";
	private static final String REQUIRED_MIN_ONE_FIELD = "either of calendar or dates is required; cannot both be blank";
	private static final String REQUIRED_EXACTLY_ONE_FIELD = "cannot provide both calendar and dates";
	private static final String REQUIRED_NOT_NULL_ELEMENT = "cannot contain a null element";

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
			errors.add("cinemaId", REQUIRED_FIELD);
		if (movieId == null)
			errors.add("movieId", REQUIRED_FIELD);
		if (dates == null && calendar == null) {
			errors.add("calendar", REQUIRED_MIN_ONE_FIELD);
			errors.add("dates", REQUIRED_MIN_ONE_FIELD);
		} else if (calendar != null && dates != null) {
			errors.add("calendar", REQUIRED_EXACTLY_ONE_FIELD);
			errors.add("dates", REQUIRED_EXACTLY_ONE_FIELD);
		} else if (dates != null && dates.isEmpty()) {
			errors.add("dates", REQUIRED_FIELD);
		} else if (calendar != null) {
			calendar.validate(errors);
		}
	}

}
