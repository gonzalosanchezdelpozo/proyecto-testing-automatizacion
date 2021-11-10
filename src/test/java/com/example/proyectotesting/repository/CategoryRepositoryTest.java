package com.example.proyectotesting.repository;

import com.example.proyectotesting.entities.Category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.Optional;

/**
 * Test para CategoryRepository en el que tendra
 * un Autowired mas la funcion de findByColor
 *
 */

@DataJpaTest
class CategoryRepositoryTest {


    @Autowired
    CategoryRepository categoryRepository;


    @BeforeEach
    void setUp() {
        System.out.println("Ejecutando test con DataJPATest");
    }

    @DisplayName("Comprobamos que en el repositorio de la categoria tenga el color que queremos")
    @Test
    void findByColor() {
        Optional<Category> categoryDB = categoryRepository.findByColor("Color Verde");
        assertNotNull(categoryDB);
        assertFalse(categoryDB.isPresent());



    }
}