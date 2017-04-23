package pl.com.bottega.cineman.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cineman.application.AdminPanel;
import pl.com.bottega.cineman.model.commands.CreateMovieCommand;
import pl.com.bottega.cineman.model.commands.DefineMoviePricesCommand;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {

	private AdminPanel adminPanel;

    public MovieController(AdminPanel adminPanel) {
        this.adminPanel = adminPanel;
    }

    @PutMapping
    void create(@RequestBody CreateMovieCommand cmd) {
        adminPanel.createMovie(cmd);
    }

    @PutMapping("/{movieId}/prices")
	void defineMoviePrices(@PathVariable Long movieId, @RequestBody Map<String, BigDecimal> pricesMap) {
		DefineMoviePricesCommand command = new DefineMoviePricesCommand();
		command.setPrices(pricesMap);
    	command.setMovieId(movieId);
    	adminPanel.defineMoviePrices(command);
	}
}