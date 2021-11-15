package com.example.proyectotesting.patterns_test.creational.builder.example;

import com.example.proyectotesting.patterns.structural.adapter.Main;
import com.example.proyectotesting.repository.DirectionRepository;
import com.example.proyectotesting.service.DirectionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class MainTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void mainTest() {

        Main.main(new String[] {});
    }
}