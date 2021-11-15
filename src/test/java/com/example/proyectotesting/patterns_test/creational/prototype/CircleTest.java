package com.example.proyectotesting.patterns_test.creational.prototype;

import com.example.proyectotesting.patterns.creational.prototype.Circle;
import com.example.proyectotesting.patterns.creational.prototype.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@Disabled
class CircleTest extends Circle{
    int radius;
    String color;
    protected CircleTest(String color, int radius) {
        super(color, radius);
    }

    @BeforeEach
    void setUp() {
        color = "red";
        radius = 5;
    }

    @Disabled
    @Test
    void copyOKTest() {
        Circle circle = new CircleTest(color,radius);
        Shape newCircle = circle.copy();

        assertNotNull(newCircle);
    }
}