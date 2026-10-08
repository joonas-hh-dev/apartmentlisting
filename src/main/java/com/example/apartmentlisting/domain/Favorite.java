package com.example.apartmentlisting.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity 
public class Favorite {

    public Favorite() {}

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @ManyToOne 
    private User user;
    @ManyToOne
    private Apartment apartment;

    public Favorite(User user, Apartment apartment) {
        this.user = user;
        this.apartment = apartment;
    }

    public User getUser() {
        return user;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }
}
