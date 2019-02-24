package com.example.demo;

import com.example.demo.user.model.User;
import com.example.demo.user.repository.UserRepository;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages ="com.example.demo")
@EntityScan(basePackages = "com.example.demo.user.model")
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user1 = new User();
		user1.setUsername("john.doe");
		user1.setPassword("thisismysecret");
		user1.setName("john");
		user1.setSurname("doe");
		String date = "15/01/1985";
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		user1.setDateOfBirth(formatter.parse(date));
		user1.setBooks("1,4");
		userRepository.save(user1);
	}
}
