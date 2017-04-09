package pl.com.bottega.cineman.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cineman.application.AdminPanel;
import pl.com.bottega.cineman.application.CinemaDto;
import pl.com.bottega.cineman.model.commands.CreateCinemaCommand;
import pl.com.bottega.cineman.model.commands.CreateShowingsCommand;

import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

	@Autowired
    private AdminPanel adminPanel;

    @PutMapping
    void create(@RequestBody CreateCinemaCommand cmd) {
        adminPanel.createCinema(cmd);
    }

    List<CinemaDto> showAll() {
        return null;
    }

    void createShowings(CreateShowingsCommand cmd) {

    }
}
