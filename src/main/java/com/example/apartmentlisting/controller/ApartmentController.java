package com.example.apartmentlisting.controller;

import java.util.Locale.Category;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.apartmentlisting.repository.ApartmentRepository;
import com.example.apartmentlisting.repository.SellerRepository;
import com.example.apartmentlisting.domain.Apartment;
import com.example.apartmentlisting.domain.Seller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


// Declare the controller
@Controller
public class ApartmentController {

    // Store the repositories
    private final ApartmentRepository apartmentRepository;
    private final SellerRepository sellerRepository;

    // Create a constructor
    ApartmentController(ApartmentRepository apartmentRepository, SellerRepository sellerRepository) {
        this.apartmentRepository = apartmentRepository;
        this.sellerRepository = sellerRepository;
    }

    // Pass the repository to the view using model.addAttribute
    @GetMapping("/apartments") 
    public String getApartmentList(Model model) {
        model.addAttribute("apartments", apartmentRepository.findAll());
        return "ApartmentList";
    }

    // Get inidividual apartment details
    @GetMapping("apartments/{id}")
    public String getApartment(@PathVariable Long id, Model model) {
        Optional<Apartment> result = apartmentRepository.findById(id);
        Apartment apartment = result.get();
        model.addAttribute("apartment", apartment);
        return "Apartment";
    }
    
    // Open add apartment form
    @GetMapping("/apartments/add")
    public String showAddApartmentForm(Model model) {
        model.addAttribute("apartment", new Apartment());
        model.addAttribute("sellers", sellerRepository.findAll());
        return "AddApartment";
    }

    // Save new apartment
    @PostMapping("/apartments/add")
    public String saveAddApartmentForm(
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
        Optional<Seller> result = sellerRepository.findById(seller);
        Seller selectedSeller = result.get();       
        Apartment apartment = new Apartment(type, address, zipCode, city, area, rooms, yearBuilt, floor, selectedSeller);
        apartmentRepository.save(apartment);
        return "redirect:/apartments";
    }

    // Delete existing apartment
    @GetMapping("/apartments/delete/{id}")
    public String deleteApartment(@PathVariable Long id, Model model) {
        apartmentRepository.deleteById(id);
        return "redirect:/apartments";
    }
    
    
}
