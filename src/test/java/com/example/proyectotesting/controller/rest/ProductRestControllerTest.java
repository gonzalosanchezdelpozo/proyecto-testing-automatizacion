package com.example.proyectotesting.controller.rest;

import com.example.proyectotesting.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductRestControllerTest {
    private static final String PRODUCTSURL = "/api/products";
    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);


    }


    @Nested
 class findTests {
       @DisplayName("Comprobación buscar todos los productos")
        @Test
       void findAll() {
           createDemoProduct();
           createDemoProduct();
           createDemoProduct();

           ResponseEntity<Product[]> result =testRestTemplate.getForEntity(PRODUCTSURL,Product[].class);
           assertEquals(200, result.getStatusCodeValue());
           assertEquals(HttpStatus.OK, result.getStatusCode());
           assertTrue(result.hasBody());
           assertNotNull(result.getBody());

           List<Product> products = List.of(result.getBody());
           assertNotNull(products);
    assertTrue(products.size() >=3);


       }
@DisplayName("comprobamos buscar uno por ID de forma correcta")
       @Test
       void findOneOk() {
    Product product =createDemoProduct();
    ResponseEntity<Product> response =testRestTemplate.getForEntity(PRODUCTSURL+"/"+product.getId(), Product.class);
    assertEquals(200, response.getStatusCodeValue());
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertTrue(response.hasBody());
    Product replyBody= response.getBody();
    assertNotNull(replyBody);
    assertNotNull(replyBody.getId());
    assertEquals(replyBody.getId(), product.getId());

       }
@DisplayName("comprobamos buscar uno Not Found")
       @Test
        void findOneNotFound(){
           ResponseEntity<Product> badDemo= testRestTemplate.getForEntity(PRODUCTSURL +"/88888", Product.class);
        assertEquals(404, badDemo.getStatusCodeValue());
        assertEquals(HttpStatus.NOT_FOUND, badDemo.getStatusCode());
        assertFalse(badDemo.hasBody());


        }
   }
 /*
    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteAll() {
    }
*/

    private Product createDemoProduct(){
        String json = """
                {
                    "name": "Product de prueba",
                    "description": "description check",
                    "quantity": 5,
                    "price": 9.99
                }
                """;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Product> response =
                testRestTemplate.postForEntity(PRODUCTSURL, request, Product.class);
        return response.getBody();
    }

}