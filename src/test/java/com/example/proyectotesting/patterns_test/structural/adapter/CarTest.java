package com.example.proyectotesting.patterns_test.structural.adapter;

import com.example.proyectotesting.patterns.structural.adapter.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    Car car;

    @BeforeEach
    void setUp() {
     car = new Car();
    }



    @Test
    void getSpeed() {
        car.getSpeed();
        assertNotNull(car.getSpeed());
    }

    @Test
    void setSpeed() {
        car.setSpeed(130);
        assertEquals(130, car.getSpeed());
    }
    @Test
    void speedUp() {
        double faster = car.getSpeed();
        car.setSpeed(20);
        assertEquals(faster+20, car.getSpeed());
    }
}