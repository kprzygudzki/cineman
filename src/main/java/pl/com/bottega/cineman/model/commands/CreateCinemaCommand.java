package pl.com.bottega.cineman.model.commands;

public class CreateCinemaCommand implements Validatable {

	private String name;
	private String city;

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
		trimAndValidateCity(errors);
		trimAndValidateName(errors);
	}

	private void trimAndValidateName(ValidationErrors errors) {
		if (name == null) {
			errors.add("name", REQUIRED_FIELD);
			return;
		}
		name = name.trim();
		if (name.isEmpty())
			errors.add("name", REQUIRED_FIELD);
	}

	private void trimAndValidateCity(ValidationErrors errors) {
		if (city == null) {
			errors.add("city", REQUIRED_FIELD);
			return;
		}
		city = city.trim();
		if (city.isEmpty())
			errors.add("city", REQUIRED_FIELD);
	}

}
