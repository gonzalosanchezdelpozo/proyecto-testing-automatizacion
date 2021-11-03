package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Manufacturer;
import com.example.proyectotesting.repository.ManufacturerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ManufacturerServiceImplTest {

    ManufacturerService manufacturerService;
    ManufacturerRepository manufacturerRepository;

    @BeforeEach
    void setUp() {
       manufacturerRepository = mock(ManufacturerRepository.class);
       this.manufacturerService = new ManufacturerServiceImpl(manufacturerRepository);

       List<Manufacturer> arrayList = new ArrayList<>();
       arrayList.add(new Manufacturer());
       arrayList.add(new Manufacturer());

       when(manufacturerRepository.findAll()).thenReturn(arrayList);

    }



    @Test
    void findAll() {

        List<Manufacturer> manufacturers = manufacturerService.findAll();

    }

    @Test
    void findOne() {
    }

    @Test
    void findByYear() {
    }

    @Test
    void save() {
    }

    @Test
    void count() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void findManufacturerByCountry() {
    }

    @Test
    void deleteAll() {
    }
}