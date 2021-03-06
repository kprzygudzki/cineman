package pl.com.bottega.cineman.infrastructure;

import pl.com.bottega.cineman.application.ReservationCatalog;
import pl.com.bottega.cineman.application.ReservationDto;
import pl.com.bottega.cineman.application.ReservationDtoBuilder;
import pl.com.bottega.cineman.application.ReservationsQuery;
import pl.com.bottega.cineman.model.PriceCalculator;
import pl.com.bottega.cineman.model.Reservation;
import pl.com.bottega.cineman.model.ReservationStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.LinkedList;
import java.util.List;

public class JPAReservationCatalog implements ReservationCatalog {

	@PersistenceContext
	private EntityManager entityManager;

	private PriceCalculator priceCalculator;

	public JPAReservationCatalog(PriceCalculator priceCalculator) {
		this.priceCalculator = priceCalculator;
	}

	@Override
	public List<ReservationDto> getReservations(ReservationsQuery reservationsQuery) {
		List<Reservation> reservations = queryReservations(reservationsQuery);
		return getReservationDtos(reservations);
	}

	private List<Reservation> queryReservations(ReservationsQuery reservationsQuery) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Reservation> criteria = builder.createQuery(Reservation.class);
		Root<Reservation> reservation = criteria.from(Reservation.class);
		Fetch showing = reservation.fetch("showing", JoinType.LEFT);
		Fetch cinema = showing.fetch("cinema", JoinType.LEFT);
		Fetch movie = showing.fetch("movie", JoinType.LEFT);
		Fetch prices = movie.fetch("pricing", JoinType.LEFT).fetch("prices", JoinType.LEFT);
		Fetch customer = reservation.fetch("customer", JoinType.LEFT);
		Fetch seats = reservation.fetch("seats", JoinType.LEFT);
		Fetch items = reservation.fetch("items", JoinType.LEFT);

		criteria.where(builder.and(preparePredicates(reservationsQuery, builder, reservation)));
		criteria.distinct(true);
		criteria.orderBy(builder.asc(reservation.get("showing").get("beginsAt")));
		List<Reservation> reservations = entityManager.createQuery(criteria).getResultList();
		injectPriceCalculator(reservations);
		return reservations;
	}

	private Predicate[] preparePredicates(
			ReservationsQuery reservationsQuery, CriteriaBuilder builder, Root<Reservation> reservation) {
		String query = "%" + reservationsQuery.getQuery().toLowerCase() + "%";
		List<Predicate> predicates = new LinkedList<>();
		predicates.add(getMatchingQueryPredicate(builder, reservation, query));
		predicates.add(getStatusPredicate(builder, reservation));
		predicates.add(getFutureShowsPredicate(builder, reservation));
		return predicates.toArray(new Predicate[]{});
	}

	private Predicate getMatchingQueryPredicate(CriteriaBuilder builder, Root<Reservation> reservation, String query) {
		return builder.like(builder.lower(reservation.get("customer").get("lastName")), query);
	}

	private Predicate getStatusPredicate(CriteriaBuilder builder, Root<Reservation> reservation) {
		//TODO rework to take a collection of statuses as a parameter
		return builder.or(
				builder.equal(reservation.get("status"), ReservationStatus.PENDING),
				builder.equal(reservation.get("status"), ReservationStatus.PAYMENT_FAILED)
		);
	}

	private Predicate getFutureShowsPredicate(CriteriaBuilder builder, Root<Reservation> reservation) {
		return builder.greaterThan(reservation.get("showing").get("endsAt"), builder.currentTimestamp());
	}

	private List<ReservationDto> getReservationDtos(List<Reservation> reservations) {
		List<ReservationDto> reservationDtos = new LinkedList<>();
		ReservationDtoBuilder builder = new ReservationDtoBuilder();
		for (Reservation reservation : reservations) {
			reservationDtos.add(createReservationDto(builder, reservation));
		}
		return reservationDtos;
	}

	private ReservationDto createReservationDto(ReservationDtoBuilder builder, Reservation reservation) {
		reservation.export(builder);
		return builder.build();
	}

	private void injectPriceCalculator(List<Reservation> reservations) {
		for (Reservation reservation : reservations)
			reservation.setPriceCalculator(priceCalculator);
	}

}
