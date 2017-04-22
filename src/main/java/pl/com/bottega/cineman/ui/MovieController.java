package pl.com.bottega.cineman.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cineman.application.AdminPanel;
import pl.com.bottega.cineman.model.MovieRepository;
import pl.com.bottega.cineman.model.commands.CreateMovieCommand;
import pl.com.bottega.cineman.model.commands.DefineMoviePricesCommand;

@RestController
@RequestMapping("/movies")
public class MovieController {

	private AdminPanel adminPanel;
	private MovieRepository movieRepository;

    public MovieController(AdminPanel adminPanel, MovieRepository movieRepository) {
        this.adminPanel = adminPanel;
        this.movieRepository = movieRepository;
    }

    @PutMapping
    void create(@RequestBody CreateMovieCommand cmd) {
        adminPanel.createMovie(cmd);
    }

    @PutMapping("/{movieId}/prices")
	void defineMoviePrices(@PathVariable Long movieId, @RequestBody DefineMoviePricesCommand command){
    	command.setMovie(movieRepository.get(movieId));
    	adminPanel.defineMoviePrices(movieId, command);
	}
}