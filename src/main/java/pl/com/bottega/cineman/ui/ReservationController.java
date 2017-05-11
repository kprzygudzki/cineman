package pl.com.bottega.cineman.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cineman.application.PaymentCollector;
import pl.com.bottega.cineman.application.ReservationCatalog;
import pl.com.bottega.cineman.application.ReservationDto;
import pl.com.bottega.cineman.application.ReservationProcess;
import pl.com.bottega.cineman.application.ReservationsQuery;
import pl.com.bottega.cineman.model.CalculationResult;
import pl.com.bottega.cineman.model.ReservationNumber;
import pl.com.bottega.cineman.model.commands.CalculatePriceCommand;
import pl.com.bottega.cineman.model.commands.CollectPaymentCommand;
import pl.com.bottega.cineman.model.commands.CreateReservationCommand;

import java.util.List;

@RestController
public class ReservationController {

	private ReservationProcess reservationProcess;
	private PaymentCollector paymentCollector;
	private ReservationCatalog reservationCatalog;

	public ReservationController(ReservationProcess reservationProcess, PaymentCollector paymentCollector, ReservationCatalog reservationCatalog) {
		this.reservationProcess = reservationProcess;
		this.paymentCollector = paymentCollector;
		this.reservationCatalog = reservationCatalog;
	}

	@PostMapping("/price_calculator")
	CalculationResult calculatePrices(@RequestBody CalculatePriceCommand cmd) {
		return reservationProcess.calculatePrices(cmd);
	}

	@PutMapping("/reservations")
	ReservationNumber create(@RequestBody CreateReservationCommand command) {
		return reservationProcess.create(command);
	}

	@GetMapping("/reservations")
	List<ReservationDto> showReservations(ReservationsQuery query) {
		return reservationCatalog.getReservations(query);
	}

	@PutMapping("/reservations/{reservationNumber}/payment")
	void collectPayment(@PathVariable String reservationNumber, @RequestBody CollectPaymentCommand command) {
		ReservationNumber number = new ReservationNumber(reservationNumber);
		command.setReservationNumber(number);
		paymentCollector.collectPayment(command);
	}

}
