package com.example.apartmentlisting.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.apartmentlisting.domain.Seller;

public interface SellerRepository extends CrudRepository<Seller, Long> {
}
