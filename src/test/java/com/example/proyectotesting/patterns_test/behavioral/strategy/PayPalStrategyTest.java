package com.example.proyectotesting.patterns_test.behavioral.strategy;

import com.example.proyectotesting.patterns.behavioral.strategy.PayPalStrategy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class PayPalStrategyTest {

    @Test
    void PayPalStrategyClassTest(){
        PayPalStrategy ps = new PayPalStrategy("Api 1","USer1,","23452");

        ps.setApi("Api edit");
        ps.setUser("User edit");
        ps.setToken("45673");


        assertEquals("Api edit", ps.getApi());
        assertEquals("User edit", ps.getUser());
        assertEquals("45673", ps.getToken());

    }

}
