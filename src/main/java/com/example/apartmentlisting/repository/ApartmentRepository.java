// Repository serves as an entry point into JPA database
// It allows creting, reading, updating and deleting apartment objects
package com.example.apartmentlisting.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.apartmentlisting.domain.Apartment;

public interface ApartmentRepository extends CrudRepository<Apartment, Long> {
}
