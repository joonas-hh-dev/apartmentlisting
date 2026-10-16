package com.example.apartmentlisting;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.apartmentlisting.domain.Apartment;
import com.example.apartmentlisting.domain.Seller;
import com.example.apartmentlisting.domain.User;
import com.example.apartmentlisting.repository.ApartmentRepository;
import com.example.apartmentlisting.repository.SellerRepository;
import com.example.apartmentlisting.repository.UserRepository;

@SpringBootApplication
public class ApartmentlistingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApartmentlistingApplication.class, args);
	}

	// Run this code when the application starts
	@Bean
	public CommandLineRunner demoRunner(SellerRepository sellerRepository, ApartmentRepository apartmentRepository, UserRepository userRepository) {
		return args -> {
			// Check if apartmentRepository is empty before adding dummy data
			if (apartmentRepository.count() == 0) {
				Seller seller = new Seller("Mikko", "Mallikas", "+358401112233", "mikko.mallikas@esimerkki.com");
				sellerRepository.save(seller);

				Seller seller2 = new Seller("Erkki", "Esimerkki", "+358502223344", "erkki.esimerkki@malli.com");
				sellerRepository.save(seller2);

				Apartment apartment = new Apartment("Kerrostalo", "Prinssintie 4 B", "00820", "Helsinki", 51.5, 2, 1956, 4, 199900, seller);
				apartmentRepository.save(apartment);

				Apartment apartment2 = new Apartment("Kerrostalo", "Sirkkalankatu 4", "20520", "Turku", 73.5, 3, 1968, 6, 278000, seller2);
				apartmentRepository.save(apartment2);

				Apartment apartment3 = new Apartment("Kerrostalo", "Aspinniemenkatu 3 A 19","33180", "Tampere", 45.0, 2, 2026, 5, 321200, seller);
				apartmentRepository.save(apartment3);
			}

			// Create new BCryptPasswordEncoder  object
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			// Check if "user" already exists to prevent duplicates
			if (userRepository.findByUsername("user").isEmpty()) {
				User user = new User("user", passwordEncoder.encode("user"), "USER");
				userRepository.save(user);
			}

			if (userRepository.findByUsername("admin").isEmpty()) {
				User user2 = new User("admin", passwordEncoder.encode("admin"), "ADMIN");
				userRepository.save(user2);
			}

		};
	}
}
