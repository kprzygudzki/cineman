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

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	@Override
	public void validate(ValidationErrors errors) {
		validateFirstName(errors);
		validateLastName(errors);
		validateEmail(errors);
		validatePhone(errors);
	}

	private void validateFirstName(ValidationErrors errors) {
		if (firstName == null)
			errors.add("firstName", "is a required field and cannot be empty");
		else {
			firstName = firstName.trim();
			if (firstName.isEmpty())
				errors.add("firstName", "is a required field and cannot be empty");
		}
	}

	private void validateLastName(ValidationErrors errors) {
		if (lastName == null)
			errors.add("lastName", "is a required field and cannot be empty");
		else {
			lastName = lastName.trim();
			if (lastName.isEmpty())
				errors.add("lastName", "is a required field and cannot be empty");
		}
	}

	private void validateEmail(ValidationErrors errors) {
		if (email == null)
			errors.add("email", "is a required field and cannot be empty");
		else {
			email = email.trim();
			if (email.isEmpty())
				errors.add("email", "is a required field and cannot be empty");
		}
	}

	private void validatePhone(ValidationErrors errors) {
		if (phone == null)
			errors.add("phone", "is a required field and cannot be empty");
		else {
			phone = phone.trim();
			if (phone.isEmpty())
				errors.add("phone", "is a required field and cannot be empty");
		}
	}

}
