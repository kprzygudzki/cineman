package pl.com.bottega.cineman.infrastructure;

import pl.com.bottega.cineman.application.MovieCatalog;
import pl.com.bottega.cineman.application.MovieDto;
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
			MovieDto dto = new MovieDto();
			dto.setId(movie.getId());
			dto.setTitle(movie.getTitle());
			dto.setDescription(movie.getDescription());
			dto.setActors(movie.getActors());
			dto.setGenres(movie.getGenres());
			dto.setMinAge(movie.getMinAge());
			dto.setLength(movie.getLength());
			movieDtos.add(dto);
		}
		return movieDtos;
	}

}
