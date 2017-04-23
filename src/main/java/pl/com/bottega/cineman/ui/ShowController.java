package pl.com.bottega.cineman.ui;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cineman.application.ReservationProcess;
import pl.com.bottega.cineman.application.ViewingRoomDto;

@RestController
@RequestMapping("/shows")
public class ShowController {

	private ReservationProcess reservationProcess;

	public ShowController(ReservationProcess reservationProcess) {
		this.reservationProcess = reservationProcess;
	}

	@GetMapping("/{showingId}/seats")
	ViewingRoomDto getSeats(@PathVariable Long showingId) {
		return reservationProcess.getSeats(showingId);
	}

}
