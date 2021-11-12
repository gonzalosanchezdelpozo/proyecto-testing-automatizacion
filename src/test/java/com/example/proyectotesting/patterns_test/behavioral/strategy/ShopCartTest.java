package com.example.proyectotesting.patterns_test.behavioral.strategy;


import com.example.proyectotesting.patterns.behavioral.strategy.CreditCardStrategy;
import com.example.proyectotesting.patterns.behavioral.strategy.PayPalStrategy;
import com.example.proyectotesting.patterns.behavioral.strategy.Product;
import com.example.proyectotesting.patterns.behavioral.strategy.ShopCart;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase que aniade  los metodos de aniadir producto,
 * borrar el producto y un calculo del pago
 *
 */


public class ShopCartTest {

    @BeforeEach
    void setUp() {
        System.out.println("Ejecutando Test de ShopCart");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Finalizando Test de ShopCart");
    }



    @DisplayName("Comprobando que se añade correctamente un producto")
    @Test
    void addProductTest(){
        List<Product> products = new ArrayList<>();

        Product p1 = new Product("1111222333",45);
        Product p2 = new Product("444555333",50);

        products.add(p1);
        products.add(p2);

        p1.setSku("222222222222");
        p1.setPrice(47);

        assertEquals("222222222222", p1.getSku());
        assertEquals(47, p1.getPrice());

        ShopCart shop_cart = new ShopCart();

        shop_cart.addProduct(p1);
        shop_cart.addProduct(p2);

        assertNotNull(shop_cart);
        assertEquals(2,products.size());



    }

    @DisplayName("Comprobando que se borran todos los productos")
    @Test
    void removeProductTest(){
        List<Product> products = new ArrayList<>();

        Product p1 = new Product("1111222333",45);
        Product p2 = new Product("444555333",50);

        products.add(p1);
        products.add(p2);

        p1.setSku("222222222222");
        p1.setPrice(47);

        assertEquals("222222222222", p1.getSku());
        assertEquals(47, p1.getPrice());

        ShopCart shop_cart = new ShopCart();
        assertEquals(2,products.size());
        shop_cart.removeProduct(p1);
        shop_cart.removeProduct(p2);

        assertNotNull(shop_cart);


    }


    @DisplayName("Comproabando que funciona la estrategia de pago utilizada ")
    @Test
    void payTest(){
        ShopCart shop_cart = new ShopCart();

        Product p1 = new Product("1111222333",45);
        Product p2 = new Product("444555333",50);

        shop_cart.addProduct(p1);
        shop_cart.addProduct(p2);

        // Crear y ejecutar estrategia (método de pago): PayPal
        shop_cart.pay(new PayPalStrategy("","",""));

        // Crear y ejecutar estrategia (método de pago): Tarjeta de crédito
        shop_cart.pay(new CreditCardStrategy("","","", ""));

        // Si la interfaz tiene un solo método entonces es una interfaz funcional y por
        // tanto se pueden utilizar lambdas, ahorrando así tener que crear las implementaciones estrategia
        shop_cart.pay(
                (amount) -> System.out.println(amount)
        );
    }


}
