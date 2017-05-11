package pl.com.bottega.cineman.model;

import pl.com.bottega.cineman.model.commands.CollectPaymentCommand;
import pl.com.bottega.cineman.model.commands.CreateReservationCommand;
import pl.com.bottega.cineman.model.commands.InvalidActionException;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static pl.com.bottega.cineman.model.PaymentType.CASH;
import static pl.com.bottega.cineman.model.ReservationStatus.*;

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

	@ManyToOne
	private Showing showing;

	public Reservation(Showing showing, CreateReservationCommand command) {
		this.showing = showing;
		this.seats = command.getSeats();
		this.items = command.getTickets();
		this.number = ReservationNumber.generate();
		this.status = PENDING;
		this.customer = command.getCustomer();
	}

	public Reservation() {
	}

	public void collectPayment(CollectPaymentCommand command) {
		ensureReservationStatus();
		preparePaymentHistory();
		if (command.getType() == CASH)
			payByCash(command);
	}

	private void preparePaymentHistory() {
		if (paymentTransactions == null)
			paymentTransactions = new HashSet<>();
	}

	private void ensureReservationStatus() {
		if (status != PENDING && status != PAYMENT_FAILED)
			throw new InvalidActionException("Reservation has to be PENDING OR PAYMENT_FAILED");
	}

	private void payByCash(CollectPaymentCommand command) {
		PaymentTransaction paymentTransaction = new PaymentTransaction(command);
		paymentTransactions.add(paymentTransaction);
		status = PAID;
	}

	public void setPaymentFacade(PaymentFacade facade) {
		paymentFacade = facade;
	}

	Set<Seat> getSeats() {
		return seats;
	}

	public ReservationNumber getNumber() {
		return number;
	}

	public void export(ReservationExporter exporter) {
		exporter.addNumber(number);
		exporter.addStatus(status);
		exporter.addSeats(seats);
		exporter.addItemsAndShowing(items, showing);
		exporter.addCustomer(customer);
	}
}
