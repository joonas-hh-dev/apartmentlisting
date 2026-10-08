package com.example.apartmentlisting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apartmentlisting.domain.Apartment;
import com.example.apartmentlisting.domain.Favorite;
import com.example.apartmentlisting.domain.User;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    
    // Check if this apartment is a favorite for this user
    boolean existsByUserAndApartment(User user, Apartment apartment);

    void deleteByUserAndApartment(User user, Apartment apartment);

    // Find a list of favorites for this user
    List<Favorite> findByUser(User user);
}