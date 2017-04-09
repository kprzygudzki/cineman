package pl.com.bottega.cineman.infrastructure;

import pl.com.bottega.cineman.application.CinemaCatalog;
import pl.com.bottega.cineman.application.CinemaDto;
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
			CinemaDto dto = new CinemaDto();
			dto.setCity(cinema.getCity());
			dto.setName(cinema.getName());
			dto.setId(cinema.getId());
			cinemaDtos.add(dto);
		}
		return cinemaDtos;
	}

}
