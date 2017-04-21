package pl.com.bottega.cineman.model.commands;

public class CreateCinemaCommand implements Validatable {

	private String name;
	private String city;

	private static final String REQUIRED_FIELD = "is a required field and cannot be blank";

	public CreateCinemaCommand() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public void validate(ValidationErrors errors) {
		if (name == null || name.isEmpty())
			errors.add("name", REQUIRED_FIELD);
		if (city == null || city.isEmpty())
			errors.add("city", REQUIRED_FIELD);
	}

}
