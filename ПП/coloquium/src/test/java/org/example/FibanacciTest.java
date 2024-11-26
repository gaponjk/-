package org.example;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FibanacciTest {
    Fibanacci fibanacci=new Fibanacci();
    @Test
    void testFibannacciZero() {
        ArrayList<Integer>arrayList=Fibanacci.fibancciCounter(2);
        ArrayList<Integer>compare=new ArrayList<>();
        compare.add(0);
        compare.add(1);
        assertEquals(arrayList,compare);
    }
    @Test
    void testFibannacciMore() {//0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55

        ArrayList<Integer>arrayList=Fibanacci.fibancciCounter(7);
        ArrayList<Integer>compare=new ArrayList<>();
        compare.add(0);
        compare.add(1);
        compare.add(1);
        compare.add(2);
        compare.add(3);
        compare.add(5);
        compare.add(8);
        assertEquals(arrayList,compare);
    }
}