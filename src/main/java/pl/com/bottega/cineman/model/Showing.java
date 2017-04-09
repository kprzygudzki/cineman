package pl.com.bottega.cineman.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Showing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Cinema cinema;

	@ManyToOne
	private Movie movie;

	private LocalDateTime beginsAt;

}
