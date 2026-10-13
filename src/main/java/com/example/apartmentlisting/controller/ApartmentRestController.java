package com.example.apartmentlisting.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.apartmentlisting.repository.ApartmentRepository;
import com.example.apartmentlisting.repository.SellerRepository;

import jakarta.validation.Valid;

import com.example.apartmentlisting.domain.Apartment;
import com.example.apartmentlisting.domain.Seller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.validation.BindingResult;

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
        @Valid @RequestBody Apartment apartment
    ) {
        return apartmentRepository.save(apartment);
    }

    // Edit existing apartment
    @PutMapping("/api/apartments/{id}")
    public Apartment apartment(
        @PathVariable Long id,
        @Valid @RequestBody Apartment apartment
    ) {
        Optional<Apartment> optionalApartment = apartmentRepository.findById(id);
        Apartment oldApartment = optionalApartment.get();
        oldApartment.setType(apartment.getType());
        oldApartment.setAddress(apartment.getAddress());
        oldApartment.setZipCode(apartment.getZipCode());
        oldApartment.setCity(apartment.getCity());
        oldApartment.setArea(apartment.getArea());
        oldApartment.setRooms(apartment.getRooms());
        oldApartment.setYearBuilt(apartment.getYearBuilt());
        oldApartment.setFloor(apartment.getFloor());
        oldApartment.setSeller(apartment.getSeller());
        return apartmentRepository.save(oldApartment);
    }

    // Delete apartment
    @DeleteMapping("/api/apartments/{id}")
    public void deleteApartment(@PathVariable Long id) {
        apartmentRepository.deleteById(id);
    }
}