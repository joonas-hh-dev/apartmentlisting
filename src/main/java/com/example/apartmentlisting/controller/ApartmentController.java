package com.example.apartmentlisting.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.apartmentlisting.repository.ApartmentRepository;
import com.example.apartmentlisting.repository.FavoriteRepository;
import com.example.apartmentlisting.repository.SellerRepository;
import com.example.apartmentlisting.repository.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import com.example.apartmentlisting.domain.Apartment;
import com.example.apartmentlisting.domain.Favorite;
import com.example.apartmentlisting.domain.Seller;
import com.example.apartmentlisting.domain.User;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



// Declare the controller
@Controller
public class ApartmentController {

    // Store the repositories
    private final ApartmentRepository apartmentRepository;
    private final SellerRepository sellerRepository;
    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;

    // Create a constructor
    ApartmentController(
        ApartmentRepository apartmentRepository,
        SellerRepository sellerRepository,
        FavoriteRepository favoriteRepository,
        UserRepository userRepository
    ) {
        this.apartmentRepository = apartmentRepository;
        this.sellerRepository = sellerRepository;
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
    }

    // Pass the repository to the view using model.addAttribute
    @GetMapping("/apartments") 
    public String getApartmentList(Model model, Principal principal) {
        model.addAttribute("apartments", apartmentRepository.findAll());
        if (principal != null) {
            String username = principal.getName();
            Optional<User> optionalUser = userRepository.findByUsername(username);
            User user = optionalUser.get();
            List<Favorite> favorites = favoriteRepository.findByUser(user);
            model.addAttribute("favorites", favorites);
        }
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
    // This was done first useing @RequestParam, but was later changed due to validation
    @Secured("ROLE_ADMIN")
    @PostMapping("/apartments/add")
    public String saveAddApartmentForm(
        @Valid @ModelAttribute("apartment") Apartment apartment,
        BindingResult bindingResult,
        Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("sellers", sellerRepository.findAll());
            return "AddApartment";
        }
        apartmentRepository.save(apartment);
        return "redirect:/apartments/add";
    }

    // Open edit apartment form
    @GetMapping("/apartments/edit/{id}")
    public String showEditApartmentForm(@PathVariable Long id, Model model) {
        Optional<Apartment> result = apartmentRepository.findById(id);
        model.addAttribute("sellers", sellerRepository.findAll());
        Apartment apartment = result.get();
        model.addAttribute("apartment", apartment);
        return "EditApartment";
    }

    // Save edited apartment
    @Secured("ROLE_ADMIN")
    @PostMapping("/apartments/edit/{id}")
    public String saveEditApartmentForm(
        @PathVariable Long id,
        @Valid @ModelAttribute("apartment") Apartment apartment,
        BindingResult bindingResult,
        Model model
    ) {
        Optional<Apartment> optionalApartment = apartmentRepository.findById(id);
        Apartment oldApartment = optionalApartment.get();
        if (bindingResult.hasErrors()) {
            model.addAttribute("sellers", sellerRepository.findAll());
            return "EditApartment";
        }
        oldApartment.setType(apartment.getType());
        oldApartment.setAddress(apartment.getAddress());
        oldApartment.setZipCode(apartment.getZipCode());
        oldApartment.setCity(apartment.getCity());
        oldApartment.setArea(apartment.getArea());
        oldApartment.setFloor(apartment.getFloor());
        oldApartment.setRooms(apartment.getRooms());
        oldApartment.setYearBuilt(apartment.getYearBuilt());
        oldApartment.setSeller(apartment.getSeller());
        apartmentRepository.save(oldApartment);
        return "redirect:/apartments";
    }

    // Delete existing apartment
    @Secured("ROLE_ADMIN")
    @GetMapping("/apartments/delete/{id}")
    public String deleteApartment(@PathVariable Long id, Model model) {
        apartmentRepository.deleteById(id);
        return "redirect:/apartments";
    }
    
    // Open login form
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // Save favorite
    @PostMapping("/favorites/add/{id}")
    public String addToFavorites(@PathVariable Long id, Principal principal) {
        Optional<Apartment> optionalApartment = apartmentRepository.findById(id);
        Apartment apartment = optionalApartment.get();
        String username = principal.getName();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        User user = optionalUser.get();
        if (!favoriteRepository.existsByUserAndApartment(user, apartment)) {
            Favorite favorite = new Favorite(user, apartment);
            favoriteRepository.save(favorite);
        }
        return "redirect:/apartments";
    }
    
    // Delete favorite
    @Transactional 
    @PostMapping("/favorites/delete/{id}")
    public String deleteFromFavorites(@PathVariable  Long id, Principal principal) {
        Optional<Apartment> optionalApartment = apartmentRepository.findById(id);
        Apartment apartment = optionalApartment.get();
        String username = principal.getName();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        User user = optionalUser.get();
        favoriteRepository.deleteByUserAndApartment(user, apartment);
        return "redirect:/favorites";
    }
    
    // Show favorites
    @GetMapping("/favorites")
    public String showFavoriteList(Model model, Principal principal) {
        String username = principal.getName();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        User user = optionalUser.get();
        List<Favorite> favorites = favoriteRepository.findByUser(user);
        model.addAttribute("favorites", favorites);
        return "Favorites";
    }

}
