package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Category;
import com.example.proyectotesting.repository.CategoryRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Comprobando test con las  operaciones CRUD sobre la entidad de Category")
class CategoryServiceImplTest {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        System.out.println("Ejecutando test con Mockito");
    }

    @Mock //dependencia
    CategoryRepository categoryRepository;

    @InjectMocks//el que depende de la dependencia
    CategoryServiceImpl service;


    @AfterEach
    void tearDown() {
        System.out.println("Finalizando test con Mockito");
    }


    @DisplayName("findAll()->Comprobamos que se encuentran todas las categorias")
    @Test
    void findAllTest() {
        List<Category> categories = Arrays.asList(
                new Category("Categoria1", "Azul"),
                new Category("Categoria2", "Verde"),
                new Category("Categoria3", "Rojo")
        );

        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> resultado = service.findAll();

        assertEquals(3, resultado.size());
        verify(categoryRepository).findAll();
    }


    @DisplayName("Buscar Categorias nulas")
    @Disabled("Test en proceso..")
    @Test
    void findAllNullTest(){
        List<Category> categories = new ArrayList<>();
        //categories.add(new Category(""));

        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> resultado = service.findAll();

        assertEquals(0, resultado.size());
        verify(categoryRepository).findAll();
    }

    @Disabled("Seguir con el findOne")
    @Test
    void findOne() {
        Optional<Category> categoryOpt = service.findOne(1L);
    }

    @Test
    void existsById() {
    }

    @Test
    void testFindOne() {
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
    void deleteAll() {
    }
}