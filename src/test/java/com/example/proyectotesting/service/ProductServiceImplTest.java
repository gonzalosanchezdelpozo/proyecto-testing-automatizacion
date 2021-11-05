package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Manufacturer;
import com.example.proyectotesting.entities.Product;
import com.example.proyectotesting.repository.ManufacturerRepository;
import com.example.proyectotesting.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
        @Test
        void count() {
            when(productRepository.count()).thenReturn(2L);
            Long result= productService.count();
            assertNotNull(result);
            assertEquals(2,result);
        }


   @Nested
   class findTest {
       @Test
       void findAll() {
           Manufacturer made = new Manufacturer();
           List<Product> products = Arrays.asList(
                   new Product("Balón", "futbol", 2, 10.99, made),
                   new Product("Balón2", "basket", 3, 16.99, made));
           when( productRepository.findAll()).thenReturn(products);
           List<Product> result = productService.findAll();
           assertNotNull(result);
           assertEquals(2, result.size());
           verify(productRepository).findAll();
       }

       @Test
       void findOneNullIdTest() {
           Optional<Product> product = productService.findOne(null);
           assertEquals(Optional.empty(),product);

       }
       @Test
       void findNegativeIdTest() {
           Optional<Product> product = productService.findOne(-3L);
           assertEquals(Optional.empty(),product);

       }
       @Test
       void findZeroIdTest() {
           Optional<Product> product = productService.findOne(0L);
           assertEquals(Optional.empty(), product);
       }
       @Test
       void findByIdTest() {
           Manufacturer made = new Manufacturer();
           List<Product> products = Arrays.asList(
                   new Product("Balón", "futbol", 2, 10.99, made),
                   new Product("Balón2", "basket", 3, 16.99, made));
           when( productRepository.findAll()).thenReturn(products);
           Optional<Product> product = productService.findOne(1L);
           assertNotNull(product);
           assertEquals(1L,product.get().getId());
       }

       @Test
       void existsByIdOkTest() {
           Manufacturer made = new Manufacturer();
           List<Product> products = Arrays.asList(
                   new Product("Balón", "futbol", 2, 10.99, made),
                   new Product("Balón2", "basket", 3, 16.99, made));
           when( productRepository.existsById(1L)).thenReturn(true);
        Boolean product=productService.existsById(1L);
        assertNotNull(product);
        assertTrue(product);

       }
       @Test
       void NotexistsByIdTest() {
           Manufacturer made = new Manufacturer();
           List<Product> products = Arrays.asList(
                   new Product("Balón", "futbol", 2, 10.99, made),
                   new Product("Balón2", "basket", 3, 16.99, made));
           when( productRepository.existsById(99L)).thenReturn(false);
           Boolean product=productService.existsById(99L);
           assertFalse(product);

       }
       @Test
       void findByPriceBetween() {
       }

       @Test
       void findByManufacturer() {
       }

       @Test
       void calculateShippingCost() {
       }
   }
    @Test
    void save() {
    }



    @Test
    void deleteById() {
    }

    @Test
    void deleteAll() {
    }


    }
