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
	private static final String NEGATIVE_FIELD = "cannot be zero or negative";

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
		if (actors == null)
			errors.add("actors", REQUIRED_FIELD);
		else{
			actors.remove(null);
			for(String actor : actors)
				if(actor.trim().equals(""))
					errors.add("actors", "cannot contain empty field");
			if (actors.isEmpty())
				errors.add("actors", REQUIRED_FIELD);
		}
		if (genres == null)
			errors.add("genres", REQUIRED_FIELD);
		else {
			genres.remove(null);
			for (String genre : genres) {
				if (genre.trim().equals(""))
					errors.add("genres", "cannot contain empty field");
			}
			if (genres.isEmpty())
				errors.add("genres", REQUIRED_FIELD);
		}
		if (minAge == null)
			errors.add("minAge", REQUIRED_FIELD);
		if (minAge <= 0)
			errors.add("minAge", NEGATIVE_FIELD);
		if (length == null)
			errors.add("length", REQUIRED_FIELD);
		if (length <= 0)
			errors.add("length", NEGATIVE_FIELD);
	}

}
