package com.example.proyectotesting.patterns_test.creational.factory;

import com.example.proyectotesting.patterns.creational.factory.Empleado;
import com.example.proyectotesting.patterns.creational.factory.EmpleadoFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmpleadoFactoryTest {

    EmpleadoFactory empleadoFactory;

    @BeforeEach
    void setUp() {
        empleadoFactory = new EmpleadoFactory();
    }

    @Test
    void getEmpleadoMECANICOTest() {
        Empleado empleado1;
           // Empleado return = empleadoFactory.
    }

    @Test
    void getEmpleadoPROGRAMADORTest() {
        Empleado empleado1;
          //  Empleado return = empleadoFactory.
    }

    @Test
    void getEmpleadoIllegalArgumentExceptionTest() {
        Empleado empleado1;
           // Empleado return = empleadoFactory.
    }
}