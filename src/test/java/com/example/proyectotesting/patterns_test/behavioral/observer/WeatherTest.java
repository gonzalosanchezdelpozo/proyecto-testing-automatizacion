package com.example.proyectotesting.patterns_test.behavioral.observer;

import com.example.proyectotesting.patterns.behavioral.observer.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherTest {


    @BeforeEach
    void setUp() {
        System.out.println("Iniciando test de Weather");
    }


    @AfterEach
    void tearDown() {
        System.out.println("Finalizando test de Weather");
    }



    @DisplayName("Comprobando que se añade un observer ")
    @Test
    void addObserverTest(){
        WeatherObserver obs = new Computer();
        Weather weather = new Weather();
        weather.addObserver(obs);
        assertNotNull(weather);

    }

    @DisplayName("Comprobando que se borra el observer")
    @Test
    void removeObserverTest(){
        List<WeatherObserver> observers = new ArrayList<>();
        WeatherObserver obs = new Computer();
        Weather weather = new Weather();
        weather.addObserver(obs);
        assertNotEquals(1, observers.size());
        weather.removeObserver(obs);


    }

    @DisplayName("Comprobando que se cambia el tiempo")
    @Test
    void changeWeatherTest(){
        Weather weather = new Weather();
        weather.changeWeather(WeatherType.SUNNY);
        assertNotNull(weather);

        WeatherObserver obs = new Computer();
        obs.update(WeatherType.RAINY);
        obs.update(WeatherType.CLOUDY);
       assertNotNull(obs);




    }




}
