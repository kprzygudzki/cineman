package pl.com.bottega.cineman.model;

import pl.com.bottega.cineman.model.commands.Validatable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer implements Validatable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;
	private String lastName;
	private String email;
	private String phone;

	public Customer(String firstName, String lastName, String email, String phone) {
		this.firstName = firstName.trim();
		this.lastName = lastName.trim();
		this.email = email.trim();
		this.phone = phone.trim();
	}

	public Customer() {
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public void validate(ValidationErrors errors) {
		if (firstName == null || firstName.isEmpty())
			errors.add("firstName", "is a required field and cannot be empty");
		if (lastName == null || lastName.isEmpty())
			errors.add("firstName", "is a required field and cannot be empty");
		if (email == null || email.isEmpty())
			errors.add("firstName", "is a required field and cannot be empty");
		if (phone == null || phone.isEmpty())
			errors.add("firstName", "is a required field and cannot be empty");
	}

}
