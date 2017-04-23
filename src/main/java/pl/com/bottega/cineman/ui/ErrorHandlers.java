package pl.com.bottega.cineman.ui;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.com.bottega.cineman.application.InvalidRequestException;
import pl.com.bottega.cineman.model.ResourceNotFoundException;
import pl.com.bottega.cineman.model.commands.DuplicateCinemaException;
import pl.com.bottega.cineman.model.commands.InvalidCommandException;
import pl.com.bottega.cineman.model.commands.Validatable;

@ControllerAdvice
public class ErrorHandlers {

	@ExceptionHandler(InvalidCommandException.class)
	public ResponseEntity<Validatable.ValidationErrors> handleInvalidCommandException(InvalidCommandException ex) {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
		return new ResponseEntity<>(
				ex.getErrors(),
				headers,
				HttpStatus.UNPROCESSABLE_ENTITY
		);
	}

	@ExceptionHandler(DuplicateCinemaException.class)
	public ResponseEntity<String> handleDuplicateRecordException() {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
		return new ResponseEntity<>(
				"{\"error\": \"Cinema with that city and name already exists\"}",
				headers,
				HttpStatus.UNPROCESSABLE_ENTITY
		);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
		return new ResponseEntity<>(
				String.format("{\"error\": \"%s\"}", ex.getMessage()),
				headers,
				HttpStatus.UNPROCESSABLE_ENTITY
		);
	}

	@ExceptionHandler(InvalidRequestException.class)
	public ResponseEntity<String> handleInvalidRequestException(InvalidRequestException ex) {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
		return new ResponseEntity<>(
				String.format("{\"error\": \"%s\"}", ex.getMessage()),
				headers,
				HttpStatus.UNPROCESSABLE_ENTITY
		);
	}

}
