package com.bardzinski.exercise;

public record Money(int value, String currency) {
    public Money{
        if(!"PLN".equals(currency)){
            throw new IllegalArgumentException();
        }
    }
}
