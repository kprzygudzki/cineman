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
		if (name == null)
			errors.add("name", "cannot be blank");
		if (city == null)
			errors.add("city", "cannot be blank");
	}

}
