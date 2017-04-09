package pl.com.bottega.cineman.infrastructure;

import org.springframework.context.annotation.Bean;
import pl.com.bottega.cineman.model.CinemaRepository;

@org.springframework.context.annotation.Configuration
public class Configuration {

	@Bean
	public CinemaRepository cinemaRepository() {
		return new JPACinemaRepository();
	}

}
