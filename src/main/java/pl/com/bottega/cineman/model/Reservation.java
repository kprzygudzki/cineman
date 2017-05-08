package pl.com.bottega.cineman.model;

import pl.com.bottega.cineman.model.commands.CollectPaymentCommand;
import pl.com.bottega.cineman.model.commands.CreateReservationCommand;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static pl.com.bottega.cineman.model.ReservationStatus.PAID;
import static pl.com.bottega.cineman.model.ReservationStatus.PAYMENT_FAILED;
import static pl.com.bottega.cineman.model.ReservationStatus.PENDING;

@Entity
public class Reservation {

	@EmbeddedId
	private ReservationNumber number;

	@Enumerated(EnumType.STRING)
	private ReservationStatus status;

	@ElementCollection
	private Set<Seat> seats;

	@ElementCollection
	private Set<ReservationItem> items;

	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<PaymentTransaction> paymentTransactions;

	@Transient
	private PaymentFacade paymentFacade;

	public Reservation(CreateReservationCommand command) {
		seats = command.getSeats();
		items = command.getTickets();
		number = ReservationNumber.generate();
		status = PENDING;
		customer = command.getCustomer();
	}

	public Reservation() {
	}

	public void collectPayment(CollectPaymentCommand command) {
		ensureReservationStatus();
		preparePaymentHistory();
		if (command.getType().equals(PaymentType.CASH))
			payByCash(command);
	}

	private void preparePaymentHistory() {
		if (this.paymentTransactions == null)
			this.paymentTransactions = new HashSet<>();
	}

	private void ensureReservationStatus() {
		if (!(this.status.equals(PENDING) || this.status.equals(PAYMENT_FAILED)))
			throw new InvalidActionException("Reservation has to be PENDING OR PAYMENT_FAILED");
	}

	private void payByCash(CollectPaymentCommand command) {
		PaymentTransaction paymentTransaction = new PaymentTransaction(command);
		paymentTransactions.add(paymentTransaction);
		this.status = PAID;
	}

	public void setPaymentFacade(PaymentFacade facade) {

	}

	Set<Seat> getSeats() {
		return seats;
	}

	public ReservationNumber getNumber() {
		return number;
	}

}
