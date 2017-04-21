package pl.com.bottega.cineman.model.commands;

import java.util.Set;

public class CreateMovieCommand implements Validatable {

	private String title;
	private String description;
	private Set<String> actors;
	private Set<String> genres;
	private Integer minAge;
	private Integer length;

	private static final String REQUIRED_FIELD = "is a required field and cannot be blank";

	public CreateMovieCommand() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<String> getActors() {
		return actors;
	}

	public void setActors(Set<String> actors) {
		this.actors = actors;
	}

	public Set<String> getGenres() {
		return genres;
	}

	public void setGenres(Set<String> genres) {
		this.genres = genres;
	}

	public Integer getMinAge() {
		return minAge;
	}

	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	@Override
	public void validate(ValidationErrors errors) {
		if (title == null || title.isEmpty())
			errors.add("title", REQUIRED_FIELD);
		if (description == null || description.isEmpty())
			errors.add("description", REQUIRED_FIELD);
		if (actors == null || actors.isEmpty())
			errors.add("actors", REQUIRED_FIELD);
		if (actors.contains(null))
			errors.add("actors", "cannot contain null values");
		if (genres == null || genres.isEmpty())
			errors.add("genres", REQUIRED_FIELD);
		if (genres.contains(null))
			errors.add("genres", "cannot contain null values");
		if (minAge == null)
			errors.add("minAge", REQUIRED_FIELD);
		if (length == null)
			errors.add("length", REQUIRED_FIELD);
	}

}
