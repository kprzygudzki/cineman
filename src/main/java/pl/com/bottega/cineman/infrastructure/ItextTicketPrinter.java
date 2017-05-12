package pl.com.bottega.cineman.infrastructure;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import pl.com.bottega.cineman.application.TicketPrinter;
import pl.com.bottega.cineman.model.ReservationNumber;
import pl.com.bottega.cineman.model.ReservationRepository;

import java.io.ByteArrayOutputStream;

public class ItextTicketPrinter implements TicketPrinter {

	private ReservationRepository reservationRepository;

	public ItextTicketPrinter(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	@Override
	public byte[] printTickets(ReservationNumber reservationNumber) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PdfWriter writer = new PdfWriter(outputStream);
		PdfDocument pdf = new PdfDocument(writer);
		Document document = new Document(pdf);
		document.add(new Paragraph("Hello World!"));
		document.close();
		return outputStream.toByteArray();
	}

}
