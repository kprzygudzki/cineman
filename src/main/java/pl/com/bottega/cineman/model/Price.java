package pl.com.bottega.cineman.model;

import pl.com.bottega.cineman.model.commands.CreatePriceCommand;

import javax.persistence.*;

@Entity
public class Price {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "regular_price"))
	private Money regular;
	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "student_price"))
	private Money student;
	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "school_price"))
	private Money school;
	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "children_price"))
	private Money children;
	@OneToOne(cascade = CascadeType.ALL)
	private Movie movie;

	public Price(CreatePriceCommand command) {
		this.regular = command.getRegular();
		this.student = command.getStudent();
		this.school = command.getSchool();
		this.children = command.getChildren();
		this.movie = command.getMovie();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRegular(Money regular) {
		this.regular = regular;
	}

	public void setStudent(Money student) {
		this.student = student;
	}

	public void setSchool(Money school) {
		this.school = school;
	}

	public void setChildren(Money children) {
		this.children = children;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public void export(PriceExporter exporter) {
		exporter.addId(id);
		exporter.addRegularPrice(regular);
		exporter.addStudentPrice(student);
		exporter.addSchoolPrice(school);
		exporter.addChildrenPrice(children);
		exporter.addMovie(movie);
	}
}
