package pl.com.bottega.cineman.infrastructure;

import pl.com.bottega.cineman.application.CinemaCatalog;
import pl.com.bottega.cineman.application.CinemaDto;
import pl.com.bottega.cineman.application.CinemaDtoBuilder;
import pl.com.bottega.cineman.model.Cinema;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

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

}
