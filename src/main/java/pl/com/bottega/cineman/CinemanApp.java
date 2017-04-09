package pl.com.bottega.cineman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CinemanApp {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(CinemanApp.class, args);
	}

}
