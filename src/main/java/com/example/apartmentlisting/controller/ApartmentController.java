package com.example.apartmentlisting.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.apartmentlisting.repository.ApartmentRepository;
import com.example.apartmentlisting.domain.Apartment;
import org.springframework.web.bind.annotation.RequestParam;

// Declare the controller
@Controller
public class ApartmentController {

    // Store the repository
    private final ApartmentRepository apartmentRepository;

    // Create a constructor
    ApartmentController(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    // Pass the repository to the view using model.addAttribute
    @GetMapping("/apartments") 
    public String apartmentList(Model model) {
        model.addAttribute("apartments", apartmentRepository.findAll());
        return "ApartmentList";
    }

    // Get inidividual apartment details
    @GetMapping("apartment/{id}")
    public String getApartment(@PathVariable Long id, Model model) {
        Optional<Apartment> result = apartmentRepository.findById(id);
        Apartment apartment = result.get();
        model.addAttribute("apartment", apartment);
        return "apartment";
    }

}
