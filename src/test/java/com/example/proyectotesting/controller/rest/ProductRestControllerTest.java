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
import static org.junit.jupiter.api.Assumptions.*;
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
       void findAllTest() {
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
       void findOneOkTest() {
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
        void findOneNotFoundTest(){
           ResponseEntity<Product> badDemo= testRestTemplate.getForEntity(PRODUCTSURL +"/88888", Product.class);
        assertEquals(404, badDemo.getStatusCodeValue());
        assertEquals(HttpStatus.NOT_FOUND, badDemo.getStatusCode());
        assertFalse(badDemo.hasBody());


        }
   }
 @Nested
 class saveTest {
   @DisplayName("comprobamos que crea correctamente")
     @Test
     void createOkTest() {
       String json = """
                {
                    "name": "Product Test",
                    "description": "description example",
                    "quantity": 9,
                    "price": 9.99
                }
                """;
ResponseEntity<Product> response=  testRestTemplate.postForEntity(PRODUCTSURL, createHttpRequest(json), Product.class);
       assertEquals(201, response.getStatusCodeValue());
       assertEquals(HttpStatus.CREATED, response.getStatusCode());
       assertTrue(response.hasBody());
       Product product = response.getBody();
       assertNotNull(product);
       assertEquals("Product Test", product.getName());

     }
     @DisplayName("comprobamos que no crea con una badrequest")
     @Test
     void createBadRequestTest() {
         String json = """
                {
                    "id": 5,
                    "name": "Product BadRequest",
                    "description": "description example",
                    "quantity": 9,
                    "price": 9.99
                }
                """;
    ResponseEntity<Product> response=  testRestTemplate.postForEntity(PRODUCTSURL, createHttpRequest(json), Product.class);
         assertEquals(400, response.getStatusCodeValue());
         assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
         assertFalse(response.hasBody());
     }
     @DisplayName("comprobamos que se modifica")
     @Test
     void updateOkTest() {

             Product product = createDemoProduct();
             String json = String.format("""
                {
                    "id": %d,
                    "name": "Product modified",
                    "description": "description example",
                    "quantity": 8,
                    "price": 8.99
                }
                """, product.getId());
             System.out.println(json);
         ResponseEntity<Product> response =
                 testRestTemplate.exchange(PRODUCTSURL, HttpMethod.PUT, createHttpRequest(json), Product.class);
         assertEquals(200, response.getStatusCodeValue());
         assertEquals(HttpStatus.OK, response.getStatusCode());
         assertTrue(response.hasBody());
         assertNotNull(response.getBody());
Product responseProduct = response.getBody();
assertEquals (product.getId(),responseProduct.getId() );
assertEquals("Product modified", responseProduct.getName());
assertNotEquals(responseProduct.getName(), product.getName());
     }
 @DisplayName("comprobamos update con Id null")
     @Test
 void updateNullTest() {
     Product product = createDemoProduct();
     String json = String.format("""
                {
                    "id": null,
                    "name": "Product modified",
                    "description": "description example",
                    "quantity": 8,
                    "price": 8.99
                }
                """, product.getId());
     System.out.println(json);
     ResponseEntity<Product> response =
             testRestTemplate.exchange(PRODUCTSURL, HttpMethod.PUT, createHttpRequest(json), Product.class);
     assertEquals(400, response.getStatusCodeValue());
     assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
 }
     @DisplayName("comprobamos update con Id no encontrada")
     @Test
     void updateIdNotFoundTest() {
         Product product = createDemoProduct();
         String json = String.format("""
                {
                    "id": 999999,
                    "name": "Product modified",
                    "description": "description example",
                    "quantity": 8,
                    "price": 8.99
                }
                """, product.getId());
         System.out.println(json);
         ResponseEntity<Product> response =
                 testRestTemplate.exchange(PRODUCTSURL, HttpMethod.PUT, createHttpRequest(json), Product.class);
         assertEquals(404, response.getStatusCodeValue());
         assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
     }

 }
 @Nested
 class deteleTests {
    @DisplayName("comprobamos que borra correctamente con una Id")
     @Test
     void deleteByIdTestOk() {
         Product product = createDemoProduct();
         String archive = PRODUCTSURL + "/" + product.getId();
         ResponseEntity<Product> response = testRestTemplate.getForEntity(archive, Product.class);
         assertEquals(200, response.getStatusCodeValue());
         assertEquals(HttpStatus.OK, response.getStatusCode());
         assertEquals(product.getId(), response.getBody().getId());
         testRestTemplate.delete(archive);
         ResponseEntity<Product> response2 = testRestTemplate.getForEntity(archive, Product.class);
         assertEquals(404, response2.getStatusCodeValue());
         assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
         assertFalse(response2.hasBody());

     }
@DisplayName("comprobamos que no borra con Id null")
     @Test
     void deleteByIdNullTest() {
         Product product = createDemoProduct();
               String archive = PRODUCTSURL + "/9999" + product.getId();
         ResponseEntity<Product> response = testRestTemplate.getForEntity(archive, Product.class);
         assertEquals(404, response.getStatusCodeValue());
         assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    ResponseEntity<Product> response2 = testRestTemplate.getForEntity(archive, Product.class);
          testRestTemplate.delete(archive);

          assertFalse(archive.isEmpty());


     }
@DisplayName("comprobamos que borramos todos")
         @Test
     void deleteAll() {
             createDemoProduct();
             createDemoProduct();
             createDemoProduct();
             ResponseEntity<Product[]> response = testRestTemplate.getForEntity(PRODUCTSURL, Product[].class);
             assertNotNull(response.getBody());
             List<Product> products = List.of(response.getBody());
             assertTrue(products.size() >= 3);
             testRestTemplate.delete(PRODUCTSURL);
    response = testRestTemplate.getForEntity(PRODUCTSURL, Product[].class);
    assertNotNull(response.getBody());
    products = List.of(response.getBody());
    assertEquals(0, products.size());
     }

     /*@DisplayName("comprobamos que borramos todos")
     @Test
     void deleteAllConflictTest() {
         testRestTemplate.delete(PRODUCTSURL);
         ResponseEntity<Product[]> response = testRestTemplate.exchange(PRODUCTSURL,HttpStatus.CONFLICT, createDemoProduct(), Product[].class);

         List<Product> products = List.of(response.getBody());

         assertTrue(products.size() == 0);
         testRestTemplate.delete(PRODUCTSURL);
         response = testRestTemplate.getForEntity(PRODUCTSURL, Product[].class);
         assertTrue(products.isEmpty());
         assertEquals(409, response.getStatusCodeValue());
         assertEquals(HttpStatus.CONFLICT, response.getStatusCode());



     }

      */
 }

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
    private HttpEntity<String> createHttpRequest(String json){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        return request;
    }
}