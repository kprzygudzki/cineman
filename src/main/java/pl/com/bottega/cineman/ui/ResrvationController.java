package pl.com.bottega.cineman.ui;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cineman.model.ReservationNumber;
import pl.com.bottega.cineman.model.commands.CreateReservationCommand;

@RestController
@RequestMapping
public class ResrvationController {

	ReservationNumber create(CreateReservationCommand command) {
		return null;
	}

}
