package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Manufacturer;
import com.example.proyectotesting.repository.ManufacturerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ManufacturerServiceImplTest {

    ManufacturerService manufacturerService;
    ManufacturerRepository manufacturerRepository;

    @BeforeEach
    void setUp() {
       manufacturerRepository = mock(ManufacturerRepository.class);
       this.manufacturerService = new ManufacturerServiceImpl(manufacturerRepository);

        List<Manufacturer> manufacturers = Arrays.asList(
                new Manufacturer("name1","cif1", 5,2021),
                new Manufacturer("name2", "cif2", 3, 2019));

    }

    @Test
    void count() {
        //configuracion escenario
        when(manufacturerRepository.count()).thenReturn(2L);
        //ejecutar el comportamiento a testear
        Long result= manufacturerService.count();
        //Aserciones y verificaciones
        assertNotNull(result);
        assertEquals(2,result);
    }

    @Test
    void findAll() {
        List<Manufacturer> manufacturers = Arrays.asList(
                new Manufacturer("name1","cif1", 5,2021),
                new Manufacturer("name2", "cif2", 3, 2019));

        when(manufacturerRepository.findAll()).thenReturn(manufacturers);

        List<Manufacturer> result = manufacturerService.findAll();
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(manufacturerRepository).findAll();
    }

    @Test
    void findOne() {

        Optional<Manufacturer> manufacturer = manufacturerService.findOne(1L);
        assertNotNull(manufacturer);
        assertEquals(1L, manufacturer.get().getId());

    }

    @Test
    void findByYear() {
      //  List<Manufacturer> manufacturers = );
        //assertNotNull(manufacturers);
        //ssertTrue(manufacturers > );
    }

    @Test
    void save() {
        //Manufacturer manufacturer = manufacturerService.save();

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