package pl.com.bottega.cineman.application;

import java.util.List;

public class MovieShowingsDto {

	private MovieDto movie;
	private List<ShowingDto> shows;

	public void setMovie(MovieDto movie) {
		this.movie = movie;
	}

	public MovieDto getMovie() {
		return movie;
	}

	public void setShows(List<ShowingDto> shows) {
		this.shows = shows;
	}

	public List<ShowingDto> getShows() {
		return shows;
	}

}
