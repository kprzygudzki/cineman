package pl.com.bottega.cineman.application;

import pl.com.bottega.cineman.model.Money;
import pl.com.bottega.cineman.model.Movie;

public class PriceDto {

	private Long id;
	private Money regular;
	private Money student;
	private Money school;
	private Money children;
	private Movie movie;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
}
