package com.example.first;

import com.example.first.entity.CustomerClassificationTypeEntity;
import com.example.first.repository.CustomerClassificationTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;

@SpringBootApplication
public class FirstApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
			}
		};
	}

	@Bean
	CommandLineRunner initData(CustomerClassificationTypeRepository classificationRepo) {
		return args -> {
			if (classificationRepo.count() == 0) {
				CustomerClassificationTypeEntity type = new CustomerClassificationTypeEntity();
				type.setCustomerClassificationType("Standard");
				type.setCustomerClassificationValue("Standard Customer");
				type.setEffectiveDate(LocalDate.now());
				classificationRepo.save(type);
			}
		};
	}
}
