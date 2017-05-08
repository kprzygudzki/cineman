package pl.com.bottega.cineman.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cineman.application.PaymentCollector;
import pl.com.bottega.cineman.application.ReservationProcess;
import pl.com.bottega.cineman.model.CalculationResult;
import pl.com.bottega.cineman.model.ReservationNumber;
import pl.com.bottega.cineman.model.commands.CalculatePriceCommand;
import pl.com.bottega.cineman.model.commands.CollectPaymentCommand;
import pl.com.bottega.cineman.model.commands.CreateReservationCommand;

@RestController
@RequestMapping
public class ReservationController {

	private ReservationProcess reservationProcess;
	private PaymentCollector paymentCollector;

	public ReservationController(ReservationProcess reservationProcess, PaymentCollector paymentCollector) {
		this.reservationProcess = reservationProcess;
		this.paymentCollector = paymentCollector;
	}

	@PostMapping("/price_calculator")
	CalculationResult calculatePrices(@RequestBody CalculatePriceCommand cmd) {
		return reservationProcess.calculatePrices(cmd);
	}

	@PutMapping("/reservations")
	ReservationNumber create(@RequestBody CreateReservationCommand command) {
		return reservationProcess.create(command);
	}

	@PutMapping("/reservations/{reservationNumber}/payment")
	void collectPayment(@PathVariable String reservationNumber, @RequestBody CollectPaymentCommand command) {
		ReservationNumber number = new ReservationNumber(reservationNumber);
		command.setReservationNumber(number);
		paymentCollector.collectPayment(command);
	}

}
