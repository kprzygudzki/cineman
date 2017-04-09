package pl.com.bottega.cineman.model.commands;

public class CreateCinemaCommand {

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

}
