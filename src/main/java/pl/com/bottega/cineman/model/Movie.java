package pl.com.bottega.cineman.model;

import pl.com.bottega.cineman.model.commands.CreateMovieCommand;
import pl.com.bottega.cineman.model.commands.DefineMoviePricesCommand;

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
	@CollectionTable(name = "actors")
	@Column(name = "actor")
	private Set<String> actors;

	@ElementCollection
	@CollectionTable(name = "genres")
	@Column(name = "genre")
	private Set<String> genres;

	private Integer minAge;
	private Integer length;

	@OneToOne
	private Pricing pricing;

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

	public void definePricing(DefineMoviePricesCommand command) {
		if (pricing == null)
			pricing = new Pricing(command);
		else
			pricing.update(command);
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

	public Long getId() {
		return id;
	}

	public Pricing getPricing() {
		return pricing;
	}

}
