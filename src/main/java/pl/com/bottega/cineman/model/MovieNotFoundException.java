package pl.com.bottega.cineman.model;

public class MovieNotFoundException extends RuntimeException {

	public MovieNotFoundException(Long id) {
		super(String.format("Movie with id %s does not exist", id));
	}

}
