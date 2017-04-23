package pl.com.bottega.cineman.model;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String resourceName, Long id) {
		super(String.format("%s with id %s does not exist", resourceName, id));
	}

}
