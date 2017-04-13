package pl.com.bottega.cineman.infrastructure;

import pl.com.bottega.cineman.application.MovieCatalog;
import pl.com.bottega.cineman.application.MovieDto;
import pl.com.bottega.cineman.application.MovieDtoBuilder;
import pl.com.bottega.cineman.model.Movie;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class JPAMovieCatalog implements MovieCatalog {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<MovieDto> getMovies() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Movie> criteria = builder.createQuery(Movie.class);
		criteria.from(Movie.class);
		List<Movie> movieList = entityManager.createQuery(criteria).getResultList();
		return createMovieDtos(movieList);
	}

	private List<MovieDto> createMovieDtos(List<Movie> movies) {
		List<MovieDto> movieDtos = new ArrayList<>();
		MovieDtoBuilder builder = new MovieDtoBuilder();
		for (Movie movie : movies) {
			movie.export(builder);
			MovieDto movieDto = builder.build();
			movieDtos.add(movieDto);
		}
		return movieDtos;
	}

}
