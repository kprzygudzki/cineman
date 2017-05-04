package pl.com.bottega.cineman.acceptance;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cineman.application.ReservationProcess;
import pl.com.bottega.cineman.model.*;
import pl.com.bottega.cineman.model.commands.CalculatePriceCommand;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class PriceCalculatorTest {

	PriceCalculator priceCalculator;

	@Test
	public void shouldCalculatePrices() {
		/*CalculatePriceCommand command = new CalculatePriceCommand();
		command.setShowId(1L);

		ReservationItem reservationItems1 = new ReservationItem();
		reservationItems1.setKind("regular");
		reservationItems1.setQuantity(2);
		ReservationItem reservationItems2 = new ReservationItem();
		reservationItems2.setKind("school");
		reservationItems2.setQuantity(1);

		CalculationItem calculationItem1 = new CalculationItem(reservationItems1, BigDecimal.valueOf(10));
		CalculationItem calculationItem2 = new CalculationItem(reservationItems2, BigDecimal.valueOf(10));

		Set<ReservationItem> reservationItems = new HashSet<>();
		reservationItems.add(reservationItems1);
		reservationItems.add(reservationItems2);
		command.setTickets(reservationItems);

		Collection<CalculationItem> calculationItems = new HashSet<>();
		calculationItems.add(calculationItem1);
		calculationItems.add(calculationItem2);
		CalculationResult calculationResult = new CalculationResult(calculationItems);

		priceCalculator.calculatePrices(command);
		//TODO*/
	}

}
