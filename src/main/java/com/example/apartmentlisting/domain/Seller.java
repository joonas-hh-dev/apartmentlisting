package com.example.apartmentlisting.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Seller {

    public Seller() {}
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    // Create one to many relationship
    // Initialize a list that can hold multiple apartments
    // Link to seller attribute in Apartment object
    @OneToMany(mappedBy = "seller") 
    private List<Apartment> apartments = new ArrayList<>();

    public Seller(String firstName, String lastName, String phoneNumber, String email, List<Apartment> apartments) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.apartments = apartments;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }
}
