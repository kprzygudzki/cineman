package pl.com.bottega.cineman.infrastructure;

import pl.com.bottega.cineman.model.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JPAReservationRepository implements ReservationRepository {

	private PaymentFacade paymentFacade;
	private PriceCalculator priceCalculator;

	public JPAReservationRepository(PaymentFacade paymentFacade, PriceCalculator priceCalculator) {
		this.paymentFacade = paymentFacade;
		this.priceCalculator = priceCalculator;
	}

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Reservation get(ReservationNumber reservationNumber) {
		Reservation reservation = entityManager.find(Reservation.class, reservationNumber);
		if (reservation == null)
			throw new ResourceNotFoundException("reservation", reservationNumber.toString());
		reservation.setPaymentFacade(paymentFacade);
		reservation.setPriceCalculator(priceCalculator);
		return reservation;
	}

}
