// Repository serves as an entry point into JPA database
// It allows creting, reading, updating and deleting apartment objects
package com.example.apartmentlisting.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.example.apartmentlisting.domain.Apartment;

// Apartment is the entity being managed
// Long is the primary key type
public interface ApartmentRepository extends CrudRepository<Apartment, Long> {
    // Find apartments by city    
    List<Apartment> findByCity(String city);
}
