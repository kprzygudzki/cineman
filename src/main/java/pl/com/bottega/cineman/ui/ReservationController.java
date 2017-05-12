package pl.com.bottega.cineman.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cineman.application.*;
import pl.com.bottega.cineman.model.CalculationResult;
import pl.com.bottega.cineman.model.ReservationNumber;
import pl.com.bottega.cineman.model.commands.CalculatePriceCommand;
import pl.com.bottega.cineman.model.commands.CollectPaymentCommand;
import pl.com.bottega.cineman.model.commands.CreateReservationCommand;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_PDF_VALUE;

@RestController
public class ReservationController {

	private ReservationProcess reservationProcess;
	private PaymentCollector paymentCollector;
	private ReservationCatalog reservationCatalog;
	private TicketPrinter ticketPrinter;

	public ReservationController(ReservationProcess reservationProcess, PaymentCollector paymentCollector,
								 ReservationCatalog reservationCatalog, TicketPrinter ticketPrinter) {
		this.reservationProcess = reservationProcess;
		this.paymentCollector = paymentCollector;
		this.reservationCatalog = reservationCatalog;
		this.ticketPrinter = ticketPrinter;
	}

	@PostMapping("/price_calculator")
	CalculationResult calculatePrices(@RequestBody CalculatePriceCommand cmd) {
		return reservationProcess.calculatePrices(cmd);
	}

	@PutMapping("/reservations")
	ReservationNumber create(@RequestBody CreateReservationCommand command) {
		return reservationProcess.create(command);
	}

	@GetMapping("/reservations")
	List<ReservationDto> showReservations(ReservationsQuery query) {
		return reservationCatalog.getReservations(query);
	}

	@PutMapping("/reservations/{reservationNumber}/payment")
	void collectPayment(@PathVariable String reservationNumber, @RequestBody CollectPaymentCommand command) {
		command.setReservationNumber(ReservationNumber.from(reservationNumber));
		paymentCollector.collectPayment(command);
	}

	@GetMapping(value = "/reservations/{reservationNumber}/tickets", produces = APPLICATION_PDF_VALUE)
	@ResponseBody
	byte[] printTicket(@PathVariable String reservationNumber) {
		return ticketPrinter.printTickets(ReservationNumber.from(reservationNumber));
	}

}
