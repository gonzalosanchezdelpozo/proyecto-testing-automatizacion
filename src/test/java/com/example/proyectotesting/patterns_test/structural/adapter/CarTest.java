package com.example.proyectotesting.patterns_test.structural.adapter;

import com.example.proyectotesting.patterns.structural.adapter.Car;
import com.example.proyectotesting.patterns.structural.adapter.Movable;
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
        car = new Car();
        car.getSpeed();
        assertNotNull(car.getSpeed());
    }

    @Test
    void setSpeed() {
        car.setSpeed(130D);
        assertEquals(130D, car.getSpeed());
    }
    @Test
    void speedUp() {
        double faster = car.getSpeed();
        car.setSpeed(20D);
        assertTrue(car.getSpeed() == car.getSpeed()+faster);
        assertEquals(faster+20D, car.getSpeed());
    }
}