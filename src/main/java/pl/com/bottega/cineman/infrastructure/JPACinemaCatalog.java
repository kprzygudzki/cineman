package pl.com.bottega.cineman.infrastructure;

import pl.com.bottega.cineman.application.*;
import pl.com.bottega.cineman.model.Cinema;
import pl.com.bottega.cineman.model.Movie;
import pl.com.bottega.cineman.model.ResourceNotFoundException;
import pl.com.bottega.cineman.model.Showing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.*;

public class JPACinemaCatalog implements CinemaCatalog {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<CinemaDto> getCinemas() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Cinema> criteria = builder.createQuery(Cinema.class);
		criteria.from(Cinema.class);
		List<Cinema> cinemas = entityManager.createQuery(criteria).getResultList();
		return createCinemaDtos(cinemas);
	}

	private List<CinemaDto> createCinemaDtos(List<Cinema> cinemas) {
		List<CinemaDto> cinemaDtos = new ArrayList<>();
		CinemaDtoBuilder builder = new CinemaDtoBuilder();
		for (Cinema cinema : cinemas) {
			cinema.export(builder);
			CinemaDto cinemaDto = builder.build();
			cinemaDtos.add(cinemaDto);
		}
		return cinemaDtos;
	}

	@Override
	public List<MovieShowingsDto> getShowings(Long cinemaId, LocalDate date) {
		ensureCinemaExists(cinemaId);
		if (date == null)
			throw new InvalidRequestException("date can not be null");
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Showing> criteria = builder.createQuery(Showing.class);
		Root<Showing> root = criteria.from(Showing.class);
		criteria.where(
				builder.equal(root.get("cinema").get("id"), cinemaId),
				builder.between(root.get("beginsAt"), date.atStartOfDay(), date.plusDays(1).atStartOfDay()));
		List<Showing> showings = entityManager.createQuery(criteria).getResultList();
		return getMovieShowingsDtos(showings);
	}

	private void ensureCinemaExists(Long id) {
		if (entityManager.find(Cinema.class, id) == null)
			throw new ResourceNotFoundException("cinema", id);
	}

	private List<MovieShowingsDto> getMovieShowingsDtos(List<Showing> showings) {
		List<MovieShowingsDto> dtos = new LinkedList<>();
		Map<Movie, List<ShowingDto>> map = getMovieShowingsMap(showings);
		MovieDtoBuilder dtoBuilder = new MovieDtoBuilder();
		for (Movie movie : map.keySet()) {
			List<ShowingDto> showingDtos = map.get(movie);
			MovieDto movieDto = createMovieDto(dtoBuilder, movie);
			MovieShowingsDto dto = createMovieShowingsDto(movieDto, showingDtos);
			dtos.add(dto);
		}
		return dtos;
	}

	private Map<Movie, List<ShowingDto>> getMovieShowingsMap(List<Showing> showings) {
		Map<Movie, List<ShowingDto>> map = new HashMap<>();
		ShowingDtoBuilder builder = new ShowingDtoBuilder();
		for (Showing showing : showings) {
			Movie movie = showing.getMovie();
			List<ShowingDto> showingDtos = map.getOrDefault(movie, new LinkedList<>());
			showingDtos.add(getShowingDto(showing, builder));
			map.put(movie, showingDtos);
		}
		return map;
	}

	private MovieShowingsDto createMovieShowingsDto(MovieDto movieDto, List<ShowingDto> showingDtos) {
		MovieShowingsDto movieShowingsDto = new MovieShowingsDto();
		movieShowingsDto.setMovie(movieDto);
		showingDtos.sort(new ShowingTimeComparator());
		movieShowingsDto.setShows(showingDtos);
		return movieShowingsDto;
	}

	private MovieDto createMovieDto(MovieDtoBuilder dtoBuilder, Movie movie) {
		movie.export(dtoBuilder);
		return dtoBuilder.build();
	}

	private ShowingDto getShowingDto(Showing showing, ShowingDtoBuilder builder) {
		showing.export(builder);
		return builder.build();
	}

	private class ShowingTimeComparator implements Comparator<ShowingDto> {

		@Override
		public int compare(ShowingDto dto1, ShowingDto dto2) {
			return dto1.getTime().compareTo(dto2.getTime());
		}

	}

}
