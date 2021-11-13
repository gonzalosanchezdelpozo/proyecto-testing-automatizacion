package com.example.proyectotesting.patterns.creational.builder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean married;

    User usuario;
    @BeforeEach
    void setUp() {
        usuario = new User(1L,"María","López","maria@gmail.es", true);
    }

    @Test
    void getIdTest() {
        Long result = usuario.getId();
        assertEquals(1L,result);
        assertNotEquals(2L, (long) result);
    }
    @Test
    void getFirstNameTest() {
        String result = usuario.getFirstName();
        assertEquals("María",result);
    }

    @Test
    void getLastNameTest() {
        String result = usuario.getLastName();
        assertEquals("López",result);
    }
    @Test
    void getEmailTest() {
        String result = usuario.getEmail();
        assertEquals("maria@gmail.es",result);
    }
    @Test
    void getMarriedTest() {
        boolean result = usuario.getMarried();
        assertTrue(result);
    }
    @Test
    void setIdTest() {
        usuario.setId(2L);
        assertEquals(2L,usuario.getId());
    }
    @Test
    void setFirstNameTest() {
        usuario.setFirstName("Pepe");
        assertEquals("Pepe",usuario.getFirstName());
    }
    @Test
    void serLastNameTest() {
        usuario.setLastName("Barba");
        assertEquals("Barba",usuario.getLastName());
    }
    @Test
    void setEmailTest() {
        usuario.setEmail("pepe@hotmail.com");
        assertEquals("pepe@hotmail.com",usuario.getEmail());
    }
    @Test
    void setMarriedTest() {
        usuario.setMarried(false);
        assertEquals(false,usuario.getMarried());
    }
    @Test
    void BuildersetIdTest() {
       new User.Builder().setId(3L);
       assertEquals(1L,usuario.getId());
    }

}