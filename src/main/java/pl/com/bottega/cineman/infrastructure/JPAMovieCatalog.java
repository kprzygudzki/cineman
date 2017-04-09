package pl.com.bottega.cineman.infrastructure;

import pl.com.bottega.cineman.application.MovieCatalog;
import pl.com.bottega.cineman.application.MovieDto;
import pl.com.bottega.cineman.application.MovieDtoBuilder;
import pl.com.bottega.cineman.model.Movie;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class JPAMovieCatalog implements MovieCatalog {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<MovieDto> getMovies() {
		List<MovieDto> movieDtos = new ArrayList<>();
		Query query = entityManager.createQuery("FROM Movie");
		List<Movie> movies = query.getResultList();
		for (Movie movie : movies) {
			MovieDtoBuilder builder = new MovieDtoBuilder();
			movie.export(builder);
			MovieDto movieDto = builder.build();
			movieDtos.add(movieDto);
		}
		return movieDtos;
	}

}
