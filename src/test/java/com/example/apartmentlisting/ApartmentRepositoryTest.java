package com.example.apartmentlisting;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import com.example.apartmentlisting.domain.Apartment;
import com.example.apartmentlisting.domain.Seller;
import com.example.apartmentlisting.repository.ApartmentRepository;
import com.example.apartmentlisting.repository.SellerRepository;

@DataJpaTest
class ApartmentRepositoryTest {

    @Autowired 
	private ApartmentRepository apartmentRepository;

    @Autowired 
    private SellerRepository sellerRepository;

    @Test 
    public void saveApartment() {
        Seller seller = new Seller("Matti", "Mallikas", "12345", "matti.mallikas@esimerkki.fi");
        sellerRepository.save(seller);

        Apartment apartment = new Apartment("Kerrostalo", "Testikatu 1", "02100", "Espoo", 65.0, 2, 1965, 6, seller);
        Apartment savedApartment = apartmentRepository.save(apartment);

        assertThat(savedApartment.getId()).isNotNull();
    }

    @Test
    public void deleteApartment() {
        Seller seller = new Seller("Matti", "Mallikas", "12345", "matti.mallikas@esimerkki.fi");
        sellerRepository.save(seller);

        Apartment apartment = new Apartment("Kerrostalo", "Testikatu 1", "02100", "Espoo", 65.0, 2, 1965, 6, seller);
        Apartment savedApartment = apartmentRepository.save(apartment);

        apartmentRepository.deleteById(savedApartment.getId());
        assertThat(apartmentRepository.findById(savedApartment.getId())).isEmpty();
    }

    @Test
    public void findByCity() {
        Seller seller = new Seller("Matti", "Mallikas", "12345", "matti.mallikas@esimerkki.fi");
        sellerRepository.save(seller);

        Apartment apartment = new Apartment("Kerrostalo", "Testikatu 1", "02100", "Espoo", 65.0, 2, 1965, 6, seller);
        apartmentRepository.save(apartment);

        List<Apartment> results = apartmentRepository.findByCity("Espoo");
        assertThat(results)
        .extracting(Apartment::getAddress)
        .contains("Testikatu 1");
    }
}

