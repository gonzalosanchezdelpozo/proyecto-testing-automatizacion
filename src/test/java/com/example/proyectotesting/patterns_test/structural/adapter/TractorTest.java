package com.example.proyectotesting.patterns_test.structural.adapter;

import com.example.proyectotesting.patterns.structural.adapter.Tractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TractorTest {

    Tractor tractor;

    @BeforeEach
    void setUp() {
        tractor = new Tractor();

    }

    @Test
    void getSpeed() {
        tractor.getSpeed();
    }

    @Test
    void setSpeed() {
    }

    @Test
    void changeMode() {
    }
}