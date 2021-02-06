package hu.bozgab;

import hu.bozgab.Repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class AppficientlyApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(AppficientlyApiApplication.class, args);
	}

}
