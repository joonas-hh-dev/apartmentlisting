package com.example.apartmentlisting.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity 
public class Apartment {

    // Create empty contructor so that JPA can create apartment objects
    public Apartment() {}

    // Crate Id so that Apartment can be saved to JPA database
    // Set Id to autogenerate
    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    // Define attributes
    // Set modifier to private so that values cannot be reassigned without setters
    private String apartmentType;
    private String address;
    private float size;
    private int rooms;
    private int yearBuilt;
    private int floor;
    @ManyToOne
    private Seller seller;

    // Define constructor
    // Set modifier to public so that other classes can create apartments
    public Apartment(String apartmentType, String address, float size, int rooms, int yearBuilt, int floor, Seller seller) {
        this.apartmentType = apartmentType;
        this.address = address;
        this.size = size;
        this.rooms = rooms;
        this.yearBuilt = yearBuilt;
        this.floor = floor;
        this.seller = seller;
    }

    // Define getters & setters to fetch & modify apartment attributes
    public Long getId() {
        return id;
    }

    public String getApartmentType() {
        return apartmentType;
    }

    public String getAddress() {
        return address;
    }

    public float getSize() {
        return size;
    }

    public int getRooms() {
        return rooms;
    }

    public int getYearBuilt() {
        return yearBuilt;
    }

    public int getFloor() {
        return floor;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public void setYearBuilt(int yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
