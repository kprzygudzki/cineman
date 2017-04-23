package pl.com.bottega.cineman.model;


import pl.com.bottega.cineman.model.commands.DefineMoviePricesCommand;

import javax.persistence.*;
import java.util.Map;

@Entity
public class Pricing {

	@Id
	@GeneratedValue
	private Long id;
	@ElementCollection
	@MapKeyColumn(name = "kind")
	@Column(name = "price")
	@CollectionTable(name = "pricing", joinColumns = @JoinColumn(name = "movie_id"))
	private Map<String, Money> prices;
	@OneToOne
	private Movie movie;

	public Pricing(){}

	public Pricing(DefineMoviePricesCommand command){
		this.prices = command.getPrices();
		this.movie = command.getMovie();
	}


}
