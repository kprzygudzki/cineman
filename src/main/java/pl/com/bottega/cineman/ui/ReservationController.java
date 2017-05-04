package pl.com.bottega.cineman.ui;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cineman.application.ReservationProcess;
import pl.com.bottega.cineman.model.CalculationResult;
import pl.com.bottega.cineman.model.commands.CalculatePriceCommand;

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

}
