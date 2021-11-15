package com.example.proyectotesting.patterns.structural.adapter;

import com.example.proyectotesting.repository.DirectionRepository;
import com.example.proyectotesting.service.DirectionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    Car car;

    @BeforeEach
    void setUp() {

    }

    @DisplayName("Comprobando el main")
    @Test
    void main() {
        Main.main(new String[] {});
    }
}