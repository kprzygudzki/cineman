package pl.com.bottega.cineman.application;

import pl.com.bottega.cineman.model.ReservationStatus;
import pl.com.bottega.cineman.model.commands.Validatable;

public class ReservationsQuery implements Validatable {

	private static final String REQUIRED_PARAMETER = "is a required parameter and can not be empty";
	private static final String INVALID_STATUS = "is not a valid reservation status";

	private String query;
	private String status;
	private ReservationStatus reservationStatus;

	public ReservationsQuery() {
	}

	public void setQ(String q) {
		this.query = q;
	}

	public void setS(String s) {
		this.status = s;
	}

	public String getQuery() {
		return query;
	}

	public ReservationStatus getStatus() {
		return reservationStatus;
	}

	@Override
	public void validate(ValidationErrors errors) {
		if (query == null || query.isEmpty())
			errors.add("query (q)", REQUIRED_PARAMETER);
		if (status == null || status.isEmpty())
			errors.add("status (s)", REQUIRED_PARAMETER);
		else
			try {
				reservationStatus = ReservationStatus.valueOf(status.toUpperCase());
			} catch (IllegalArgumentException ex) {
				errors.add("status (s)", INVALID_STATUS);
			}
	}

}
