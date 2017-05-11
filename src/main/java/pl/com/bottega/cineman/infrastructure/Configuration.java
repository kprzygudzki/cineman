package pl.com.bottega.cineman.infrastructure;

import org.springframework.context.annotation.Bean;
import pl.com.bottega.cineman.application.*;
import pl.com.bottega.cineman.application.impl.StandardAdminPanel;
import pl.com.bottega.cineman.application.impl.StandardPaymentCollector;
import pl.com.bottega.cineman.application.impl.StandardReservationProcess;
import pl.com.bottega.cineman.model.*;

@org.springframework.context.annotation.Configuration
public class Configuration {

	@Bean
	public CinemaRepository cinemaRepository() {
		return new JPACinemaRepository();
	}

	@Bean
	public CinemaCatalog cinemaCatalog() {
		return new JPACinemaCatalog();
	}

	@Bean
	public MovieRepository movieRepository() {
		return new JPAMovieRepository();
	}

	@Bean
	public MovieCatalog movieCatalog() {
		return new JPAMovieCatalog();
	}

	@Bean
	public ShowingRepository showingRepository() {
		return new JPAShowingRepository();
	}

	@Bean
	public PricingRepository pricingRepository(){
		return new JPAPricingRepository();
	}

	@Bean
	public ReservationRepository reservationRepository() {
		return new JPAReservationRepository();
	}

	@Bean
	public AdminPanel adminPanel(CinemaRepository cinemaRepository,
								 MovieRepository movieRepository,
								 ShowingRepository showingRepository,
								 PricingRepository pricingRepository) {
		return new StandardAdminPanel(cinemaRepository, movieRepository, showingRepository, pricingRepository);
	}

	@Bean
	public ReservationProcess reservationProcess(PriceCalculator priceCalculator, ShowingRepository showingRepository) {
		return new StandardReservationProcess(priceCalculator, showingRepository);
	}

	@Bean
	public PaymentCollector paymentCollector(ReservationRepository reservationRepository) {
		return new StandardPaymentCollector(reservationRepository);
	}

	@Bean
	PriceCalculator priceCalculator() {
		return new StandardPriceCalculator(showingRepository());
	}

	@Bean
	ReservationCatalog reservationCatalog() {
		return new JPAReservationCatalog();
	}

}
