package ua.com.foxminded.yuriy.schedulewebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class UniversityScheduleWebapp {

	public static void main(String[] args) {
		SpringApplication.run(UniversityScheduleWebapp.class, args);
	}
	
	 @Bean
    public FlywayMigrationStrategy flywayMigrationStrategy() {
        return flyway -> {
            flyway.clean(); // Clean database
            flyway.migrate(); // Run all migrations
        };
    }
}
