package pl.com.bottega.cineman.application;

import java.util.Set;

public class MovieDto {

	private Long id;
	private String title;
	private String description;
	private Set<String> actors;
	private Set<String> genres;
	private Integer minAge;
	private Integer length;

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

	public Integer getMinAge() {
		return minAge;
	}

	void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	public Set<String> getGenres() {
		return genres;
	}

	void setGenres(Set<String> genres) {
		this.genres = genres;
	}

	public Integer getLength() {
		return length;
	}

	void setLength(Integer length) {
		this.length = length;
	}

	public Long getId() {
		return id;
	}

	void setId(Long id) {
		this.id = id;
	}

}
