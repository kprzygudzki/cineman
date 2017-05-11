package pl.com.bottega.cineman.model.commands;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public interface Validatable {

	String REQUIRED_FIELD = "is a required field and can not be empty";
	String FUTURE_DATE_REQUIRED = "must be in the future";
	String NON_NULL_ELEMENT = "is a required field and can not contain empty elements";
	String UNIQUE_TICKET_KINDS = "must contain unique ticket kinds";
	String POSITIVE_REQUIRED = "must be bigger than 0";
	String POSITIVE_OR_ZERO_REQUIRED = "can not be less than 0";
	String EQUAL_REQUIRED = "must be equal";
	String MAX_ONE_REQUIRED = "either is required; can't both be blank";
	String MIN_ONE_REQUIRED = "either is required; can't provide both";

	void validate(ValidationErrors errors);

	class ValidationErrors {

		private Map<String, Set<String>> errors = new HashMap<>();

		public void add(String fieldName, String errorMessage) {
			Set<String> fieldErrors = errors.getOrDefault(fieldName, new HashSet<>());
			fieldErrors.add(errorMessage);
			errors.putIfAbsent(fieldName, fieldErrors);
		}

		public boolean isValid() {
			return errors.isEmpty();
		}

		public Map<String, Set<String>> getErrors() {
			return new HashMap<>(errors);
		}

	}

}
