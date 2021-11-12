package com.example.proyectotesting.patterns_test.structural.adapter;

import com.example.proyectotesting.patterns.structural.adapter.Tractor;
import com.example.proyectotesting.patterns.structural.adapter.TractorAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TractorAdapterTest {

    TractorAdapter tractorAdapter;

    @BeforeEach
    void setUp() {
        tractorAdapter = new TractorAdapter();
    }

    @Test
    @DisplayName("Speeds up a primera marcha")
    void speedUp1Gear() {
        try {
            tractorAdapter = new TractorAdapter();
            tractorAdapter.speedUp(14);
            assertTrue(true);
        }catch(Exception error){
            error.printStackTrace();
            assertTrue(false);
        }
    }
    @Test
    @DisplayName("Speeds up a segunda marchar")
    void speedUp2Gear() {
        try {
            tractorAdapter = new TractorAdapter();
            tractorAdapter.speedUp(17);
            assertTrue(true);
        }catch(Exception error){
            error.printStackTrace();
            assertTrue(false);
        }
    }
}