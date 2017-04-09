package pl.com.bottega.cineman.ui;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cineman.application.AdminPanel;
import pl.com.bottega.cineman.model.commands.CreateMovieCommand;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private AdminPanel adminPanel;

    @PutMapping
    void create(@RequestBody CreateMovieCommand cmd) {
        adminPanel.createMovie(cmd);
    }

}
