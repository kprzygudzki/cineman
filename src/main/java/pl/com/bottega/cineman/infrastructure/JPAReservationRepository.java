package pl.com.bottega.cineman.infrastructure;

import pl.com.bottega.cineman.model.Reservation;
import pl.com.bottega.cineman.model.ReservationNumber;
import pl.com.bottega.cineman.model.ReservationRepository;
import pl.com.bottega.cineman.model.ResourceNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JPAReservationRepository implements ReservationRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Reservation get(ReservationNumber reservationNumber) {
		Reservation reservation = entityManager.find(Reservation.class, reservationNumber);
		if (reservation == null)
			throw new ResourceNotFoundException("reservation", reservationNumber.toString());
		return reservation;
	}

}
