package pl.com.bottega.cineman.ui;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cineman.application.ReservationProcess;
import pl.com.bottega.cineman.model.ReservationNumber;
import pl.com.bottega.cineman.model.Showing;
import pl.com.bottega.cineman.model.ShowingRepository;
import pl.com.bottega.cineman.model.commands.CreateReservationCommand;

@RestController
@RequestMapping
public class ResrvationController {

	private ReservationProcess reservationProcess;

	public ResrvationController(ReservationProcess reservationProcess) {
		this.reservationProcess = reservationProcess;
	}

	@PostMapping("/reservations")
	ReservationNumber create(@RequestBody CreateReservationCommand command) {
		ReservationNumber reservationNumber = reservationProcess.create(command);
		return reservationNumber;
	}

}
