package pl.com.bottega.cineman.application.impl;

import pl.com.bottega.cineman.application.PaymentCollector;
import pl.com.bottega.cineman.model.Reservation;
import pl.com.bottega.cineman.model.ReservationRepository;
import pl.com.bottega.cineman.model.commands.CollectPaymentCommand;

import javax.transaction.Transactional;

@Transactional
public class StandardPaymentCollector implements PaymentCollector {

	private ReservationRepository reservationRepository;

	public StandardPaymentCollector(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	@Override
	public void collectPayment(CollectPaymentCommand command) {
		Reservation reservation = reservationRepository.get(command.getReservationNumber());
		reservation.collectPayment(command);
	}

}
