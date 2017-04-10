package pl.com.bottega.cineman.infrastructure;

import pl.com.bottega.cineman.application.CinemaCatalog;
import pl.com.bottega.cineman.application.CinemaDto;
import pl.com.bottega.cineman.application.CinemaDtoBuilder;
import pl.com.bottega.cineman.model.Cinema;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class JPACinemaCatalog implements CinemaCatalog {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<CinemaDto> getCinemas() {
		List<CinemaDto> cinemaDtos = new ArrayList<>();
		Query query = entityManager.createQuery("FROM Cinema");
		List<Cinema> cinemas = query.getResultList();
		for (Cinema cinema : cinemas) {
			CinemaDtoBuilder builder = new CinemaDtoBuilder();
			cinema.export(builder);
			CinemaDto cinemaDto = builder.build();
			cinemaDtos.add(cinemaDto);
		}
		return cinemaDtos;
	}

}
