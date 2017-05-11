package pl.com.bottega.cineman.model.commands;

import java.util.Set;

public class CreateMovieCommand implements Validatable {

	private static final String REQUIRED_FIELD = "is a required field and can not be blank";
	private static final String EMPTY_FIELDS = "is a required field and can not contain empty fields";
	private static final String NEGATIVE_FIELD = "must be at least 1";

	private String title;
	private String description;
	private Set<String> actors;
	private Set<String> genres;
	private Integer minAge;
	private Integer length;

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
		validateTitle(errors);
		validateDescription(errors);
		validateActors(errors);
		validateGenres(errors);
		validateMinAge(errors);
		validateLength(errors);
	}

	private void validateLength(ValidationErrors errors) {
		if (length == null)
			errors.add("length", REQUIRED_FIELD);
		else if (length.compareTo(1) < 0)
			errors.add("length", NEGATIVE_FIELD);
	}

	private void validateMinAge(ValidationErrors errors) {
		if (minAge == null)
			errors.add("minAge", REQUIRED_FIELD);
		else if (minAge.compareTo(1) < 0)
			errors.add("minAge", NEGATIVE_FIELD);
	}

	private void validateTitle(ValidationErrors errors) {
		if (title == null) {
			errors.add("title", REQUIRED_FIELD);
			return;
		}
		title = title.trim();
		if (title.isEmpty())
			errors.add("title", REQUIRED_FIELD);
	}

	private void validateDescription(ValidationErrors errors) {
		if (description == null) {
			errors.add("description", REQUIRED_FIELD);
			return;
		}
		description = description.trim();
		if (description.isEmpty())
			errors.add("description", REQUIRED_FIELD);
	}

	private void validateActors(ValidationErrors errors) {
		if (actors == null) {
			errors.add("actors", REQUIRED_FIELD);
			return;
		}
		if (actors.remove(null))
			errors.add("actors", EMPTY_FIELDS);
		for (String actor : actors)
			if (actor.trim().isEmpty())
				errors.add("actors", EMPTY_FIELDS);
	}

	private void validateGenres(ValidationErrors errors) {
		if (genres == null) {
			errors.add("genres", REQUIRED_FIELD);
			return;
		}
		if (genres.remove(null))
			errors.add("actors", EMPTY_FIELDS);
		for (String genre : genres)
			if (genre.trim().isEmpty())
				errors.add("genres", EMPTY_FIELDS);
	}

}
