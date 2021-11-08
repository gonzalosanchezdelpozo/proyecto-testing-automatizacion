package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Manufacturer;
import com.example.proyectotesting.repository.ManufacturerRepository;
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
        when(manufacturerRepository.count()).thenReturn(2L);
        Long result= manufacturerService.count();

        assertNotNull(result);
        assertEquals(2,result);
    }

    @Test
    void findAllTest() {
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
    void findOneIdTest() {
        List<Manufacturer> manufacturers = new ArrayList<>();

        Manufacturer manufacturer1 = new Manufacturer("name1", "12345", 4, 2007);
        manufacturer1.setId(1L);
        manufacturers.add(manufacturer1);

        assertEquals(1, manufacturers.size());

        Optional<Manufacturer> manufacturer = manufacturerService.findOne(1L);
        assertNotNull(manufacturer);
        assertTrue(manufacturer.isEmpty());
    }

    @Test
    void findOneNullTest() {
        Optional<Manufacturer> manufacturer = manufacturerService.findOne((Long) null);
        assertTrue(manufacturer.isEmpty());
    }

    @Test
    void findByYearTest() {
        List<Manufacturer> manufacturer = manufacturerService.findByYear(2021);
        assertNotNull(manufacturer);;
    }

    @Test
    void findYearNullTest() {

        List<Manufacturer> manufacturer =
                manufacturerService.findByYear(null);
        assertTrue(manufacturer.isEmpty());
    }

    @Test
    void findYearEmptyTest() {

        List<Manufacturer> manufacturer =
                manufacturerService.findByYear(0);
        assertTrue(manufacturer.isEmpty());
    }

    @Test
    void saveOKTest() {
        when(manufacturerRepository.save(any(Manufacturer.class)))
                .thenReturn(new Manufacturer());
        Manufacturer manufacturer = manufacturerService.save(new Manufacturer());
        assertNotNull(manufacturer);
    }

    @Test
    void saveNullTest() {
        assertNull(manufacturerService.save(null));
    }



    @Test
    void deleteById() {
    //when(manufacturerService.deleteById(1L)
      //      .thenReturn(manufacturerService.deleteById(1L);
    //verify(manufacturerRepository).deleteById(1L);
    }

    @Test
    void findManufacturerByCountryTest() {

    List<Manufacturer> manufacturers = manufacturerRepository.findManufacturerByDirectionCountry("España");
        assertNotNull(manufacturers);
        verify(manufacturerRepository).findManufacturerByDirectionCountry("España");

    }

    @Test
    void deleteAll() {
        manufacturerService.deleteAll();
        verify(manufacturerRepository).deleteAll();

    }
}