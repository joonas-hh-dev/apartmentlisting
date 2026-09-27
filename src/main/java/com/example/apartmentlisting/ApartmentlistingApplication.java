package com.example.apartmentlisting;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.apartmentlisting.domain.Apartment;
import com.example.apartmentlisting.domain.Seller;
import com.example.apartmentlisting.repository.ApartmentRepository;
import com.example.apartmentlisting.repository.SellerRepository;

@SpringBootApplication
public class ApartmentlistingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApartmentlistingApplication.class, args);
	}

	// Initialize Seller object first
	@Bean
	public CommandLineRunner demoRunner(SellerRepository sellerRepository, ApartmentRepository apartmentRepository) {
		return args -> {
			Seller seller = new Seller("Mikko", "Mallikas", "+358401112233", "mikko.mallikas@esimerkki.com");
			sellerRepository.save(seller);

			Seller seller2 = new Seller("Erkki", "Esimerkki", "+358502223344", "erkki.esimerkki@malli.com");
			sellerRepository.save(seller2);

			Apartment apartment = new Apartment("Kerrostalo", "Ulvilantie 11 b C 80", "00350", "Helsinki", 33.5f, 1, 1955, 8, seller);
			apartmentRepository.save(apartment);

			Apartment apartment2 = new Apartment("Kerrostalo", "Sirkkalankatu 7 B 20", "20500", "Turku", 35.0f, 1, 1972, 4, seller2);
			apartmentRepository.save(apartment2);

			Apartment apartment3 = new Apartment("Kerrostalo", "Niittaajankatu 10 B 19","00810", "Helsinki", 33.0f, 1, 2019, 2, seller);
			apartmentRepository.save(apartment3);

			Apartment apartment4 = new Apartment("Kerrostalo", "Vähä Hämeenkatu 5 C 15", "20500", "Turku", 30.0f, 1, 1968, 3, seller2);
			apartmentRepository.save(apartment4);
		};
	}
}
