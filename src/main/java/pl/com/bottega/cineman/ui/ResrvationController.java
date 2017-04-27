package pl.com.bottega.cineman.ui;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cineman.model.ReservationNumber;
import pl.com.bottega.cineman.model.Showing;
import pl.com.bottega.cineman.model.ShowingRepository;
import pl.com.bottega.cineman.model.commands.CreateReservationCommand;

@RestController
@RequestMapping
public class ResrvationController {

	private ShowingRepository showingRepository;

	public ResrvationController(ShowingRepository showingRepository) {
		this.showingRepository = showingRepository;
	}

	@PostMapping("/reservations")
	ReservationNumber create(@RequestBody CreateReservationCommand command) {
		Showing showing = showingRepository.get(command.getShowingId());
		ReservationNumber reservationNumber = showing.createReservation(command);
		showingRepository.put(showing);
		return reservationNumber;
	}

}
