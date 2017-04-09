package pl.com.bottega.cineman.model;

import pl.com.bottega.cineman.model.commands.CreateCinemaCommand;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cinema {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String city;

	public Cinema(CreateCinemaCommand command) {
		this.name = command.getName();
		this.city = command.getCity();
	}

	public Cinema() {
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

}
