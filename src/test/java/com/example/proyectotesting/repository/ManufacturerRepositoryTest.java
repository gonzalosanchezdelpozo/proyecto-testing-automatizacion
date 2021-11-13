package com.example.proyectotesting.repository;

import com.example.proyectotesting.entities.Manufacturer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManufacturerRepositoryTest {

    @Autowired
    ManufacturerRepository manufacturerRepository;

    @BeforeEach
    void setUp() {
        List<Manufacturer> manufacturers = Arrays.asList(
                new Manufacturer("name1","cif1", 5,2021),
                new Manufacturer("name2", "cif2", 3, 2019));
    }

    @Test
    void findByYearTest() {
        List<Manufacturer> manufacturers1 = manufacturerRepository.findByYear(2021);
       List<Manufacturer> manufacturers2 = manufacturerRepository.findByYear(2019);
       //assertNotNull(manufacturers);
       assertEquals(2, manufacturerRepository.count());
       //assertEquals("name1", manufacturers.get(0).getName());
    }

    @Test
    void findManufacturerByDirectionCountry() {
    }
}