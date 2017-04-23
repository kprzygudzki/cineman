package pl.com.bottega.cineman.application;

import java.util.List;
import java.util.Set;

public class MovieShowingsDto {

	private String title;
	private String description;
	private Set<String> actors;
	private Set<String> genres;
	private Integer minAge;
	private Integer length;
	private List<ShowingDto> shows;

	public String getTitle() {
		return title;
	}

	void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	void setDescription(String description) {
		this.description = description;
	}

	public Set<String> getActors() {
		return actors;
	}

	void setActors(Set<String> actors) {
		this.actors = actors;
	}

	public Set<String> getGenres() {
		return genres;
	}

	void setGenres(Set<String> genres) {
		this.genres = genres;
	}

	public Integer getMinAge() {
		return minAge;
	}

	void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	public Integer getLength() {
		return length;
	}

	void setLength(Integer length) {
		this.length = length;
	}

	public List<ShowingDto> getShows() {
		return shows;
	}

	public void setShows(List<ShowingDto> shows) {
		this.shows = shows;
	}

}
