package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Direction;
import com.example.proyectotesting.entities.Manufacturer;
import com.example.proyectotesting.entities.Product;
import com.example.proyectotesting.repository.ManufacturerRepository;
import com.example.proyectotesting.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
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
       void OptionalfindByIdTest() {
          List<Product> products =new ArrayList<>();
           Manufacturer made = new Manufacturer();

           Product product1= new Product("Balón", "futbol", 2, 10.99, made);
           System.out.println(products);
           product1.setId(1L);
           products.add(product1);
           assertEquals(1,products.size());
           System.out.println(product1);
           Optional<Product> product = productService.findOne(1L);
           assertNotNull(product);
           assertTrue(product.isEmpty());

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
           System.out.println();
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


       @Test
       void findByManufacturerEmptyTest() {

           List<Product> manufacturer= productService.findByManufacturer(null);
           assertTrue(manufacturer.isEmpty());
       }


       @Test
       void calculateShippingCostNullTest() {
           Manufacturer made = new Manufacturer();
           Product product1= new Product("Balón", "futbol", 2, 10.99, made);
           Direction direction1 = new Direction(null, "33010", "León", "Spain");
            Double shipping= productService.calculateShippingCost(null,direction1);
            assertEquals(0d, shipping);
       }
       @Test
       void calculateShippingCostSpainTest() {
           Manufacturer made = new Manufacturer();
           Product product1= new Product("Balón", "futbol", 2, 10.99, made);
           Direction direction1 = new Direction("Calle melancolia", "33010", "León", "Spain");
           Double shipping= productService.calculateShippingCost(product1,direction1);
           assertEquals(2.99d, shipping);

       }
       @Test
       void calculateShippingCostPOutSpainTest() {
           Manufacturer made = new Manufacturer();
           Product product1= new Product("Balón", "futbol", 2, 10.99, made);
           Direction direction1 = new Direction("liberty strees", "33010", "utah", "USA");
           Double shipping= productService.calculateShippingCost(product1,direction1);
           assertEquals(22.990000000000002, shipping);// bug decimales

       }
   }
   /*
    @Test
    void save(Manufacturer artengo) {
    }



    @Test
    void deleteById() {
    }

    @Test
    void deleteAll() {
    }
*/

    }
