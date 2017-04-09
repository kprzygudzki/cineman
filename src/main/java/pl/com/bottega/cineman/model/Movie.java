package pl.com.bottega.cineman.model;

import pl.com.bottega.cineman.model.commands.CreateMovieCommand;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String description;
	@ElementCollection
	private Set<String> actors;
	@ElementCollection
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

	public void export(MovieExporter exporter) {
		exporter.addId(id);
		exporter.addTitle(title);
		exporter.addDescription(description);
		exporter.addActors(actors);
		exporter.addGenres(genres);
		exporter.addMinAge(minAge);
		exporter.addLength(length);
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

}
