package pl.com.bottega.cineman.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cineman.application.AdminPanel;
import pl.com.bottega.cineman.application.CinemaDto;
import pl.com.bottega.cineman.model.commands.CreateCinemaCommand;
import pl.com.bottega.cineman.model.commands.CreateShowingsCommand;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    private AdminPanel adminPanel;

	public CinemaController(AdminPanel adminPanel) {
		this.adminPanel = adminPanel;
	}

    @PutMapping
    void create(@RequestBody CreateCinemaCommand command) {
        adminPanel.createCinema(command);
    }

    List<CinemaDto> showAll() {
        return null;
    }

    @PutMapping("/{cinemaId}/shows")
    void createShowings(@PathVariable Long cinemaId, @RequestBody CreateShowingsCommand command) {
		command.setCinemaId(cinemaId);
    	adminPanel.createShowings(command);
	}

}
