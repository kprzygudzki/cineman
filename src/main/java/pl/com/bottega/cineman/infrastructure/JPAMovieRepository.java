package pl.com.bottega.cineman.infrastructure;

import pl.com.bottega.cineman.model.Movie;
import pl.com.bottega.cineman.model.MovieRepository;
import pl.com.bottega.cineman.model.ResourceNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JPAMovieRepository implements MovieRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void put(Movie movie) {
		entityManager.persist(movie);
	}

	@Override
	public Movie get(Long id) {
		Movie movie = entityManager.find(Movie.class, id);
		if(movie == null)
			throw new ResourceNotFoundException("movie", id);
		return movie;
	}

}
