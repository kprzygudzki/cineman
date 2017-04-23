package pl.com.bottega.cineman.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.com.bottega.cineman.application.ReservationProcess;
import pl.com.bottega.cineman.application.ViewingRoomDto;

@Controller
@RequestMapping("/shows")
public class ShowController {

	ReservationProcess reservationProcess;

	@GetMapping("/{showId}/seats")
	ViewingRoomDto getSeats(Long showId) {
		return reservationProcess.getSeats(showId);
	}

}
