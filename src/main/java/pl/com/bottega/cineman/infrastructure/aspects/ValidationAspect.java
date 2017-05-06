package pl.com.bottega.cineman.infrastructure.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import pl.com.bottega.cineman.model.commands.InvalidCommandException;
import pl.com.bottega.cineman.model.commands.Validatable;
import pl.com.bottega.cineman.model.commands.Validatable.ValidationErrors;

@Component
@Aspect
public class ValidationAspect {

	@Before( "execution(* pl.com.bottega.cineman.application..*.*(..)) && args(validatable,..)" )
	public void validate(Validatable validatable) {
		ValidationErrors errors = new ValidationErrors();
		validatable.validate(errors);
		if (!errors.isValid())
			throw new InvalidCommandException(errors);
	}

}
