package pl.com.bottega.cineman.model.commands;

public class InvalidActionException extends RuntimeException {
	public InvalidActionException(String message) {
		super(message);
	}
}
