package pl.com.bottega.cineman.model;

import pl.com.bottega.cineman.model.commands.CalculatePriceCommand;
import pl.com.bottega.cineman.model.commands.CollectPaymentCommand;
import pl.com.bottega.cineman.model.commands.CreateReservationCommand;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static pl.com.bottega.cineman.model.PaymentType.CASH;
import static pl.com.bottega.cineman.model.PaymentType.CREDIT_CARD;
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

	@ManyToOne
	private Showing showing;

	@Transient
	private PaymentFacade paymentFacade;

	@Transient
	private PriceCalculator priceCalculator;

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
		status.ensureCanAcceptPayment();
		preparePaymentHistory();
		if (command.getType() == CASH)
			payByCash(command);
		else if (command.getType() == CREDIT_CARD)
			payByCreditCard(command);
	}

	private void payByCash(CollectPaymentCommand command) {
		PaymentTransaction paymentTransaction = new PaymentTransaction(command);
		paymentTransactions.add(paymentTransaction);
		status = PAID;
	}

	private void payByCreditCard(CollectPaymentCommand command) {
		CreditCard creditCard = command.getCreditCard();
		CalculationResult calculationResult = getCalculationResult();
		ChargeResult chargeResult = paymentFacade.charge(creditCard, calculationResult.getTotalPrice());
		PaymentTransaction paymentTransaction = new PaymentTransaction(chargeResult);
		paymentTransactions.add(paymentTransaction);
		if (chargeResult.isPaid())
			status = PAID;
		else
			status = PAYMENT_FAILED;
	}

	private CalculationResult getCalculationResult() {
		CalculatePriceCommand calculateCommand = new CalculatePriceCommand();
		calculateCommand.setShowId(showing.getId());
		calculateCommand.setTickets(items);
		return priceCalculator.calculatePrices(calculateCommand);
	}

	private void preparePaymentHistory() {
		if (paymentTransactions == null)
			paymentTransactions = new HashSet<>();
	}

	public void setPaymentFacade(PaymentFacade facade) {
		paymentFacade = facade;
	}

	public void setPriceCalculator(PriceCalculator priceCalculator) {
		this.priceCalculator = priceCalculator;
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
		exporter.addItems(items);
		exporter.addShowing(showing);
		exporter.addCustomer(customer);
		exporter.addCalculationResult(getCalculationResult());
	}

}
