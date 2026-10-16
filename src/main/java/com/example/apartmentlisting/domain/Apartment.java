package com.example.apartmentlisting.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;;

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
    // Set validation for each attribute
    @NotBlank(message = "Valitse asuntotyyppi")
    private String type;

    @NotBlank(message = "Lisää osoite")
    private String address;

    @NotBlank(message = "Lisää postinumero")
    private String zipCode;

    @NotBlank(message = "Lisää kaupunki")
    private String city;

    @NotNull(message = "Lisää pinta-ala")
    @DecimalMin(value = "1.0", message = "Pinta-alan on oltava vähintään 1 m²")
    private Double area;

    @NotNull(message = "Lisää huoneiden määrä")
    @Min(value = 1, message = "Huoneiden vähimmäismäärä on 1")
    private Integer rooms;

    @NotNull(message = "Lisää rakennusvuosi")
    @Min(value = 1800, message = "Rakennusvuoden on oltava vähintään 1800")
    private Integer yearBuilt;

    @NotNull(message = "Lisää kerros")
    @Min(value = -1, message = "Kerroksen on oltava vähintään -1")
    private Integer floor;

    @NotNull(message = "Lisää hinta")
    @Min(value = 1, message = "Hinnan on oltava vähintään 1 €")
    private Integer price;

    @NotNull(message = "Valitse myyjä")
    @ManyToOne
    private Seller seller;

    // Define constructor
    // Set modifier to public so that other classes can create apartments
    public Apartment(String type, String address, String zipCode, String city, Double area, int rooms, int yearBuilt, int floor, int price, Seller seller) {
        this.type = type;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.area = area;
        this.rooms = rooms;
        this.yearBuilt = yearBuilt;
        this.floor = floor;
        this.price = price;
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

    public Double getArea() {
        return area;
    }

    public Integer getRooms() {
        return rooms;
    }

    public Integer getYearBuilt() {
        return yearBuilt;
    }

    public Integer getFloor() {
        return floor;
    }

    public Integer getPrice() {
        return price;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
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

    public void setArea(Double area) {
        this.area = area;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public void setYearBuilt(Integer yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
