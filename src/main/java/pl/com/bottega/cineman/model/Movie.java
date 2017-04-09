package pl.com.bottega.cineman.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;

@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String description;
	private Set<String> actors;
	private Set<String> genres;
	private Integer minAge;
	private Integer length;

	public Movie(CreateMovieCommand command) {
		this.title = command.getTitle();
		this.description = command.getDescription();
		this.actors = command.getActors();
		this.genres = command.getGenres();
		this.minAge = command.getMinAge();
		this.length = command.getLength();
	}

	public Movie() {
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setActors(Set<String> actors) {
		this.actors = actors;
	}

	public void setGenres(Set<String> genres) {
		this.genres = genres;
	}

	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Set<String> getActors() {
		return actors;
	}

	public Set<String> getGenres() {
		return genres;
	}

	public Integer getMinAge() {
		return minAge;
	}

	public Integer getLength() {
		return length;
	}

}
