package pl.com.bottega.cineman.model.commands;

import java.time.LocalDateTime;
import java.util.List;

public class CreateShowingsCommand implements Validatable {

	private Long cinemaId;
	private Long movieId;
	private List<LocalDateTime> dates;
	private Calendar calendar;

	private static final String REQUIRED_FIELD = "is a required field and can't be blank";
	private static final String MAX_ONE_REQUIRED = "either calendar or dates is required; can't both be blank";
	private static final String MIN_ONE_REQUIRED = "either calendar or dates is required; can't provide both";

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
	public void trimAndValidate(ValidationErrors errors) {
		if (cinemaId == null)
			errors.add("cinemaId", REQUIRED_FIELD);
		if (movieId == null)
			errors.add("movieId", REQUIRED_FIELD);
		if (dates == null && calendar == null) {
			errors.add("calendar", MAX_ONE_REQUIRED);
			errors.add("dates", MAX_ONE_REQUIRED);
		} else if (calendar != null && dates != null) {
			errors.add("calendar", MIN_ONE_REQUIRED);
			errors.add("dates", MIN_ONE_REQUIRED);
		} else if (dates != null) {
			dates.remove(null);
			if (dates.isEmpty())
				errors.add("dates", REQUIRED_FIELD);
		} else {
			calendar.trimAndValidate(errors);
		}
	}

}
