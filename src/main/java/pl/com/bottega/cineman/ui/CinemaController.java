package pl.com.bottega.cineman.ui;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cineman.application.AdminPanel;
import pl.com.bottega.cineman.application.CinemaCatalog;
import pl.com.bottega.cineman.application.CinemaDto;
import pl.com.bottega.cineman.application.MovieShowingsDto;
import pl.com.bottega.cineman.model.commands.CreateCinemaCommand;
import pl.com.bottega.cineman.model.commands.CreateShowingsCommand;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

	private CinemaCatalog cinemaCatalog;
	private AdminPanel adminPanel;

	public CinemaController(CinemaCatalog cinemaCatalog, AdminPanel adminPanel) {
		this.cinemaCatalog = cinemaCatalog;
		this.adminPanel = adminPanel;
	}

	@PutMapping
	void create(@RequestBody CreateCinemaCommand command) {
		adminPanel.createCinema(command);
	}

	@GetMapping
	List<CinemaDto> showAll() {
		return cinemaCatalog.getCinemas();
	}

	@PutMapping("/{cinemaId}/shows")
	void createShowings(@PathVariable Long cinemaId, @RequestBody CreateShowingsRequest commandDto) {
		commandDto.setCinemaId(cinemaId);
		CreateShowingsCommand command = commandDto.getCreateShowingsCommand();
		adminPanel.createShowings(command);
	}

	@GetMapping("/{cinemaId}/movies")
	List<MovieShowingsDto> getShowings(
			@PathVariable Long cinemaId, @RequestParam @DateTimeFormat(pattern = "yyyy/MM/dd") LocalDate date) {
		return cinemaCatalog.getShowings(cinemaId, date);
	}

}
