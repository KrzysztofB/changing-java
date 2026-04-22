package com.bardzinski.exercise;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyScannerTest {

    @Test
    public void demo() {
        //reads string, file, stream, console
        Scanner scanner = new Scanner("hello,1,FF");
        scanner.useDelimiter(",");
        String greeting = scanner.next(); //Numbers
        int one = scanner.nextInt();
        int ff = scanner.nextInt(16);
        scanner.close();

        assertEquals("hello", greeting);
        assertEquals(1, one);
        assertEquals(255,ff);
    }
}
