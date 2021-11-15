package com.example.proyectotesting.patterns_test.creational.builder;

import com.example.proyectotesting.patterns.creational.builder.User;
import com.example.proyectotesting.patterns.structural.adapter.Main;
import com.example.proyectotesting.repository.DirectionRepository;
import com.example.proyectotesting.service.DirectionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MainTest {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean married;

    User user;
    @BeforeEach
    void setUp() {
        //user = mock(User.class);
        this.user = new User(1L,"María","López","maria@gmail.es", true);
        when(user.getId()).thenReturn(1L);
    }
@Disabled
    @Test
    void main() {
        Main.main(new String[] {});
    }
}