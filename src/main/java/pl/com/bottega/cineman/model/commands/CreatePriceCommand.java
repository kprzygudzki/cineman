package pl.com.bottega.cineman.model.commands;

import pl.com.bottega.cineman.model.Money;
import pl.com.bottega.cineman.model.Movie;

public class CreatePriceCommand implements Validatable {

	private Money regular;
	private Money student;
	private Money school;
	private Money children;
	private Movie movie;

	private static final String REQUIRED_FIELD = "is a required field and cannot be blank";

	public CreatePriceCommand() {
	}

	public Money getRegular() {
		return regular;
	}

	public void setRegular(Money regular) {
		this.regular = regular;
	}

	public Money getStudent() {
		return student;
	}

	public void setStudent(Money student) {
		this.student = student;
	}

	public Money getSchool() {
		return school;
	}

	public void setSchool(Money school) {
		this.school = school;
	}

	public Money getChildren() {
		return children;
	}

	public void setChildren(Money children) {
		this.children = children;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	@Override
	public void validate(ValidationErrors errors) {
		if (regular == null)
			errors.add("regular", REQUIRED_FIELD);
		if (student == null)
			errors.add("student", REQUIRED_FIELD);
	}
}
