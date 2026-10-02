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
    private String type;
    private String address;
    private String zipCode;
    private String city;
    private String area;
    private int rooms;
    private int yearBuilt;
    private int floor;
    @ManyToOne
    private Seller seller;

    // Define constructor
    // Set modifier to public so that other classes can create apartments
    public Apartment(String type, String address, String zipCode, String city, String area, int rooms, int yearBuilt, int floor, Seller seller) {
        this.type = type;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.area = area;
        this.rooms = rooms;
        this.yearBuilt = yearBuilt;
        this.floor = floor;
        this.seller = seller;
    }

    // Define getters & setters to fetch & modify apartment attributes
    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getAddress() {
        return address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getArea() {
        return area;
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

    public void setApartmentType(String type) {
        this.type = type;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setArea(String area) {
        this.area = area;
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
