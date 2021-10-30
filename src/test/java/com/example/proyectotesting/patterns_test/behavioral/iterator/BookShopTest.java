package com.example.proyectotesting.patterns_test.behavioral.iterator;

import com.example.proyectotesting.patterns.behavioral.iterator.Book;
import com.example.proyectotesting.patterns.behavioral.iterator.BookShop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Operaciones añadirLibro e iterar")
class BookShopTest {


    @BeforeEach
    void setUp() {

        System.out.println("Ejecutando Test");
    }


    @Test
    void addBook() {

        List<Book> books = Arrays.asList(
                new Book("5656435", "Hawkins", 2021),
                new Book("124234fdfg", "Tolle", 1997),
                new Book("42536357657", "David", 1500)
        );




    }


}