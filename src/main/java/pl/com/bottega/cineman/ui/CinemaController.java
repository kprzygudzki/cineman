package pl.com.bottega.cineman.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cineman.application.AdminPanel;
import pl.com.bottega.cineman.application.CinemaCatalog;
import pl.com.bottega.cineman.application.CinemaDto;
import pl.com.bottega.cineman.model.commands.CreateCinemaCommand;
import pl.com.bottega.cineman.model.commands.CreateShowingsCommand;

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
	void createShowings(@PathVariable Long cinemaId, @RequestBody CreateShowingsCommandDto commandDto) {
		commandDto.setCinemaId(cinemaId);
		CreateShowingsCommand command = commandDto.getCreateShowingsCommand();
		adminPanel.createShowings(command);
	}

}
