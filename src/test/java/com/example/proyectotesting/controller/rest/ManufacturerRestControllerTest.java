package com.example.proyectotesting.controller.rest;

import com.example.proyectotesting.entities.Manufacturer;
import com.example.proyectotesting.entities.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
class ManufacturerRestControllerTest {

    private static final String MANUFACTURER_URL = "/api/manufacturers";
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



    @Test
    void findAllTest() {
        createDemoManufacturer();
        createDemoManufacturer();

        ResponseEntity<Manufacturer[]> result = testRestTemplate.getForEntity(MANUFACTURER_URL,Manufacturer[].class);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertTrue(result.hasBody());
        assertNotNull(result.getBody());

        List<Manufacturer> manufacturers = List.of(result.getBody());
        assertNotNull(manufacturers);
        assertTrue(manufacturers.size() >=2);
    }

    @Test
    void findOne() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteAll() {
    }

    private Product createDemoManufacturer(){
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
                testRestTemplate.postForEntity(MANUFACTURER_URL, request, Product.class);
        return response.getBody();
    }
    private HttpEntity<String> createHttpRequest(String json){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        return request;
    }
}