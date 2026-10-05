package com.example.apartmentlisting.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.apartmentlisting.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
