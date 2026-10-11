package com.example.apartmentlisting.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.apartmentlisting.repository.ApartmentRepository;
import com.example.apartmentlisting.repository.SellerRepository;
import com.example.apartmentlisting.domain.Apartment;
import com.example.apartmentlisting.domain.Seller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.web.csrf.CsrfToken;

@RestController
public class ApartmentRestController {

    private ApartmentRepository apartmentRepository;
    private SellerRepository sellerRepository;

    public ApartmentRestController(ApartmentRepository apartmentRepository, SellerRepository sellerRepository) {
        this.apartmentRepository = apartmentRepository;
        this.sellerRepository = sellerRepository;
    }

    // Show all apartments
    @GetMapping("/api/apartments")
    public Iterable<Apartment> findAllApartments() {
        return apartmentRepository.findAll();
    }

    // Find apartment by ID
    @GetMapping("/api/apartments/{id}")
    public Optional<Apartment> findById(@PathVariable("id") Long apartmentId) {
        return apartmentRepository.findById(apartmentId);
    }

    // Temporary endpoint for CSRF token
    @GetMapping("/api/csrf")
    public CsrfToken csrf(CsrfToken token) {
        return token;
    }

    // Add new apartment
    @PostMapping("/api/apartments")
    public Apartment apartment(
        @RequestParam String type,
        @RequestParam String address,
        @RequestParam String zipCode,
        @RequestParam String city,
        @RequestParam String area,
        @RequestParam int rooms,
        @RequestParam int yearBuilt,
        @RequestParam int floor,
        @RequestParam Long seller) {
            Optional<Seller> result = sellerRepository.findById(seller);
            Seller selectedSeller = result.get();       
            Apartment apartment = new Apartment(type, address, zipCode, city, area, rooms, yearBuilt, floor, selectedSeller);
            return apartmentRepository.save(apartment);
    }

    // Edit existing apartment
    @PutMapping("/api/apartments/{id}")
    public Apartment apartment(
        @PathVariable Long id,
        @RequestParam String type,
        @RequestParam String address,
        @RequestParam String zipCode,
        @RequestParam String city,
        @RequestParam String area,
        @RequestParam int rooms,
        @RequestParam int yearBuilt,
        @RequestParam int floor,
        @RequestParam Long seller
    ) {
        Optional<Apartment> optionalApartment = apartmentRepository.findById(id);
        Apartment apartment = optionalApartment.get();
        Optional<Seller> optionalSeller = sellerRepository.findById(seller);
        Seller sellerId = optionalSeller.get();
        apartment.setApartmentType(type);
        apartment.setAddress(address);
        apartment.setZipCode(zipCode);
        apartment.setCity(city);
        apartment.setArea(area);
        apartment.setRooms(rooms);
        apartment.setYearBuilt(yearBuilt);
        apartment.setFloor(floor);
        apartment.setSeller(sellerId);
        return apartmentRepository.save(apartment);
    }

    // Delete apartment
    @DeleteMapping("/api/apartments/{id}")
    public void deleteApartment(@PathVariable Long id) {
        apartmentRepository.deleteById(id);
    }
}