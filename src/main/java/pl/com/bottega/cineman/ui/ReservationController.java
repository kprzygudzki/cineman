package pl.com.bottega.cineman.ui;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cineman.application.ReservationProcess;
import pl.com.bottega.cineman.model.ReservationNumber;
import pl.com.bottega.cineman.model.commands.CreateReservationCommand;

@RestController
@RequestMapping
public class ReservationController {

	private ReservationProcess reservationProcess;

	public ReservationController(ReservationProcess reservationProcess) {
		this.reservationProcess = reservationProcess;
	}

	@PutMapping("/reservations")
	ReservationNumber create(@RequestBody CreateReservationCommand command) {
		ReservationNumber reservationNumber = reservationProcess.create(command);
		return reservationNumber;
	}

}
