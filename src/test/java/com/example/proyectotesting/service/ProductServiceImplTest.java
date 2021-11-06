package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Direction;
import com.example.proyectotesting.entities.Manufacturer;
import com.example.proyectotesting.entities.Product;
import com.example.proyectotesting.repository.ManufacturerRepository;
import com.example.proyectotesting.repository.ProductRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {
    ProductService productService;
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        this.productService = new ProductServiceImpl(productRepository);
        Manufacturer made = new Manufacturer();
        List<Product> products = Arrays.asList(
                new Product("Balón", "futbol", 2, 10.99, made),
                new Product("Balón2", "basket", 3, 16.99, made));

    }
@DisplayName("comprobamos que cuenta los productos")
    @Test
    void count() {
        when(productRepository.count()).thenReturn(2L);
        Long result = productService.count();
        assertNotNull(result);
        assertEquals(2, result);
    }


    @Nested
    class findTest {
        @DisplayName("comprobamos que es capaz de encontrar todos los registros")
        @Test
        void findAll() {
            Manufacturer made = new Manufacturer();
            List<Product> products = Arrays.asList(
                    new Product("Balón", "futbol", 2, 10.99, made),
                    new Product("Balón2", "basket", 3, 16.99, made));
            when(productRepository.findAll()).thenReturn(products);
            List<Product> result = productService.findAll();
            assertNotNull(result);
            assertEquals(2, result.size());
            verify(productRepository).findAll();
        }
        @DisplayName("comprobamos que no  encuentra con Id nula")
        @Test
        void findOneNullIdTest() {
            Optional<Product> product = productService.findOne(null);
            assertEquals(Optional.empty(), product);

        }
        @DisplayName("comprobamos que no  encuentra con Id negativa")
        @Test
        void findNegativeIdTest() {
            Optional<Product> product = productService.findOne(-3L);
            assertEquals(Optional.empty(), product);

        }
        @DisplayName("comprobamos que no  encuentra con Id cero")
        @Test
        void findZeroIdTest() {
            Optional<Product> product = productService.findOne(0L);
            assertEquals(Optional.empty(), product);
        }

        @Test
        void OptionalfindByIdTest() {
            List<Product> products = new ArrayList<>();
            Manufacturer made = new Manufacturer();

            Product product1 = new Product("Balón", "futbol", 2, 10.99, made);
            System.out.println(products);
            product1.setId(1L);
            products.add(product1);
            assertEquals(1, products.size());
            System.out.println(product1);
            Optional<Product> product = productService.findOne(1L);
            assertNotNull(product);
            assertTrue(product.isEmpty());

        }
        @DisplayName("comprobamos que  encuentra una Id")
        @Test
        void existsByIdOkTest() {
            Manufacturer made = new Manufacturer();
            List<Product> products = Arrays.asList(
                    new Product("Balón", "futbol", 2, 10.99, made),
                    new Product("Balón2", "basket", 3, 16.99, made));
            when(productRepository.existsById(1L)).thenReturn(true);
            Boolean product = productService.existsById(1L);
            assertNotNull(product);
            assertTrue(product);
            System.out.println();
        }
        @DisplayName("comprobamos que no encuentra una Id que no existe")
        @Test
        void NotexistsByIdTest() {
            Manufacturer made = new Manufacturer();
            List<Product> products = Arrays.asList(
                    new Product("Balón", "futbol", 2, 10.99, made),
                    new Product("Balón2", "basket", 3, 16.99, made));
            when(productRepository.existsById(99L)).thenReturn(false);
            Boolean product = productService.existsById(99L);
            assertFalse(product);

        }
        @DisplayName("comprobamos que no encuentra precios nulos")
        @Test
        void findByPriceBetweenNullTest() {
            Double pricemin = 9.99;
            Double pricemax = 14.99;
            Manufacturer made = new Manufacturer();
            List<Product> products = new ArrayList<>();
            Product product1 = new Product("Balón", "futbol", 2, 10.99, made);
            product1.setId(1L);

            products.add(product1);
            Product product2 = (new Product("Balón2", "basket", 3, 16.99, made));
            product1.setId(1L);

            products.add(product2);
            System.out.println(products);
            when(productService.findByPriceBetween(pricemin, pricemax)).thenReturn(null);
            List<Product> prices = productService.findByPriceBetween(pricemin, pricemax);
            System.out.println(prices);
            assertNull(prices);
        }

        @DisplayName("comprobamos que no encuentra productos con manufacturer nulo")
        @Test
        void findByManufacturerEmptyTest() {

            List<Product> manufacturer = productService.findByManufacturer(null);
            assertTrue(manufacturer.isEmpty());
        }

        @DisplayName("comprobamos que no calcula los portes con producto nulo")
        @Test
        void calculateShippingCostNullTest() {
            Manufacturer made = new Manufacturer();
            Product product1 = new Product("Balón", "futbol", 2, 10.99, made);
            Direction direction1 = new Direction(null, "33010", "León", "Spain");
            Double shipping = productService.calculateShippingCost(null, direction1);
            assertEquals(0d, shipping);
        }
        @DisplayName("comprobamos que calcula los portes con producto en Spain")
        @Test
        void calculateShippingCostSpainTest() {
            Manufacturer made = new Manufacturer();
            Product product1 = new Product("Balón", "futbol", 2, 10.99, made);
            Direction direction1 = new Direction("Calle melancolia", "33010", "León", "Spain");
            Double shipping = productService.calculateShippingCost(product1, direction1);
            assertEquals(2.99d, shipping);

        }
        @DisplayName("comprobamos que calcula los portes con producto fuera de Spain")
        @Test
        void calculateShippingCostPOutSpainTest() {
            Double cost1 = 2.99;
            Double calculo = (cost1 += 20);
            Manufacturer made = new Manufacturer();
            Product product1 = new Product("Balón", "futbol", 2, 10.99, made);
            Direction direction1 = new Direction("liberty strees", "33010", "utah", "USA");
            Double shipping = productService.calculateShippingCost(product1, direction1);
            assertEquals(calculo, shipping);

        }
    }

    @Nested
    class saveTest {
        @DisplayName("comprobamos que no guarda con un producto nulo")
        @Test
        void saveNullTest() {
            Manufacturer made = new Manufacturer();
            Product product1 = new Product("Balón Test", "futbol 7", 5, 18.99, made);
            when(productRepository.save(any())).thenReturn(null);
            Product result = productService.save(null);
            assertNull(result);
            Assert.assertNull(result);

        }
        @DisplayName("comprobamos que guarda bien  un producto")
        @Test
        void saveOkTest() {
            Manufacturer made = new Manufacturer();
            Product product1 = new Product("Balón Test", "futbol 7", 5, 18.99, made);
            when(productRepository.save(any())).thenReturn(product1);
            Product result = productService.save(product1);
            assertNotNull(result);
            assertEquals("Balón Test", result.getName());

        }
    }

    @Nested
    class deleteTests {
        @DisplayName("comprobamos que no borra con una Id nula")
        @Test
        void deleteByIdNullTest() {
            productService.deleteById(null);
            boolean result = productService.deleteById(null);
            assertFalse(result);


        }
        @DisplayName("comprobamos que  borra con una Id")
        @Test
        void deleteByIdOkTest() {
            productService.deleteById(1L);
            boolean result = productService.deleteById(1L);
            List<Product> borrado = productService.findAll();

            assertEquals(0, borrado.size());


        }
        @DisplayName("comprobamos que  borra todos los productos")
        @Test
        void deleteAllOk() {
            productService.deleteAll();
            verify(productRepository).deleteAll();
        }
        @DisplayName("comprobamos que devuelve una excepción al intentar  borrar todos los productos")
        @Test
        void deleteAllExceptTest() {
            productService.deleteAll();
            when(productService.deleteAll()).thenThrow(new IllegalArgumentException());

            verify(productRepository).deleteAll();

        }

    }
}
