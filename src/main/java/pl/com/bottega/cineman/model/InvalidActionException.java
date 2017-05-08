package pl.com.bottega.cineman.model;

public class InvalidActionException extends RuntimeException {
	public InvalidActionException(String message) {
		super(message);
	}
}
