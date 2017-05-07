package pl.com.bottega.cineman.model.commands;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public interface Validatable {

	void trimAndValidate(ValidationErrors errors);

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
