package com.example.proyectotesting.controller.rest;

import com.example.proyectotesting.entities.Category;
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
public class CategoryRestControllerTest {

    private static final String Category_URL = "/api/categories";
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @Test
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }


    @Nested
    class findTests{
        @DisplayName("Comprobando si al buscar la categoria se encuentran todos")
        @Test
        void findAllTest() {
            //Data para Categories
            createDataCategories();
            createDataCategories();
            createDataCategories();

            ResponseEntity<Category[]> respuesta = testRestTemplate.getForEntity(Category_URL, Category[].class);

            assertEquals(200,respuesta.getStatusCodeValue());
            assertEquals(HttpStatus.OK,respuesta.getStatusCode());
            assertTrue(respuesta.hasBody());
            assertNotNull(respuesta.getBody());

            List<Category> categories = List.of(respuesta.getBody());
            assertNotNull(categories);
            assertTrue(categories.size() >= 3);


        }

        @DisplayName("Comprobamos si encuentra una categoria")
        @Test
        void findOneCategoryTest() {
            Category category = createDataCategories();
            ResponseEntity<Category> respuesta =
                    testRestTemplate.getForEntity(Category_URL + "/" + category.getId(), Category.class);

            assertEquals(200, respuesta.getStatusCodeValue());
            assertEquals(HttpStatus.OK, respuesta.getStatusCode());
            assertTrue(respuesta.hasBody());

            Category responseCategory = respuesta.getBody();
            assertNotNull(responseCategory);
            assertNotNull(responseCategory.getId());
            assertEquals(category.getId(), responseCategory.getId());

        }

        @DisplayName("Comprobamos que no encuentra el id que le pusimos")
        @Test
        void findOneDontFound() {
            ResponseEntity<Category> response =
                    testRestTemplate.getForEntity(Category_URL + "/777", Category.class);

            assertEquals(404, response.getStatusCodeValue());
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
            assertFalse(response.hasBody());
        }


    }



    private Category createDataCategories(){
        String json = """
                {
                    "name": " Categoria creada desde Rest",
                    "color": "Color maravilloso"
              
                }
                """;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Category> response =
                testRestTemplate.postForEntity(Category_URL, request, Category.class);
        return response.getBody();
    }


}
