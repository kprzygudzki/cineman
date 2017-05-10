package pl.com.bottega.cineman.integration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.com.bottega.cineman.application.InvalidRequestException;
import pl.com.bottega.cineman.model.*;
import pl.com.bottega.cineman.model.commands.CalculatePriceCommand;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PriceCalculatorTest {

	@Autowired
	private PriceCalculator priceCalculator;

	@MockBean
	private ShowingRepository showingRepository;

	private long showingId = 587L;

	@Before
	public void setUp() throws Exception {
		Showing showing = Mockito.mock(Showing.class);
		Pricing pricing = Mockito.mock(Pricing.class);
		Mockito.when(showing.getPricing()).thenReturn(pricing);
		Map<String, BigDecimal> prices = createPrices();
		Mockito.when(pricing.getPrices()).thenReturn(prices);
		given(this.showingRepository.get(showingId)).willReturn(showing);
	}

	@Test
	public void shouldCalculatePrice() {
		CalculatePriceCommand command = prepareCalculatePriceCommand(showingId, createTickets());
		CalculationResult calculationResult = priceCalculator.calculatePrices(command);
		assertThat(calculationResult.getCalculationItems().size()).isEqualTo(2);
		assertThat(calculationResult.getTotalPrice()).isEqualTo(BigDecimal.valueOf(49L));
	}

	@Test
	public void shouldThrowExceptionWhenUndefinedTicketKindIsUsed() {
		Set<ReservationItem> tickets = createTickets();
		tickets.add(createTicket("school", 3));
		CalculatePriceCommand command = prepareCalculatePriceCommand(showingId, tickets);
		Throwable thrown = catchThrowable( () -> priceCalculator.calculatePrices(command) );
		assertThat(thrown).isInstanceOf(InvalidRequestException.class);
		assertThat(thrown.getMessage()).isEqualTo("Pricing for kind school has not been defined");
	}

	private Map<String, BigDecimal> createPrices() {
		Map<String, BigDecimal> prices = new HashMap<>();
		prices.put("regular", BigDecimal.valueOf(17L));
		prices.put("student", BigDecimal.valueOf(15L));
		return prices;
	}

	private CalculatePriceCommand prepareCalculatePriceCommand(long showingId, Set<ReservationItem> tickets) {
		CalculatePriceCommand command = new CalculatePriceCommand();
		command.setTickets(tickets);
		command.setShowId(showingId);
		return command;
	}

	private Set<ReservationItem> createTickets() {
		Set<ReservationItem> tickets = new HashSet<>();
		tickets.add(createTicket("regular", 2));
		tickets.add(createTicket("student", 1));
		return tickets;
	}

	private ReservationItem createTicket(String kind, int count) {
		ReservationItem reservationItem = new ReservationItem();
		reservationItem.setKind(kind);
		reservationItem.setCount(count);
		return reservationItem;
	}
}
