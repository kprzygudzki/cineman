package pl.com.bottega.cineman.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cineman.application.ReservationProcess;
import pl.com.bottega.cineman.model.CalculationResult;
import pl.com.bottega.cineman.model.ReservationNumber;
import pl.com.bottega.cineman.model.commands.CalculatePriceCommand;
import pl.com.bottega.cineman.model.commands.CreateReservationCommand;

@RestController
@RequestMapping
public class ReservationController {

	private ReservationProcess reservationProcess;

	public ReservationController(ReservationProcess reservationProcess) {
		this.reservationProcess = reservationProcess;
	}

	@PostMapping("/price_calculator")
	private CalculationResult calculatePrices(@RequestBody CalculatePriceCommand cmd) {
		return reservationProcess.calculatePrices(cmd);
	}

	@PutMapping("/reservations")
	ReservationNumber create(@RequestBody CreateReservationCommand command) {
		ReservationNumber reservationNumber = reservationProcess.create(command);
		return reservationNumber;
	}

}
