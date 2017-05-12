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

	private Cinema() {
	}

	public void export(CinemaExporter exporter) {
		exporter.addId(id);
		exporter.addName(name);
		exporter.addCity(city);
	}

}
