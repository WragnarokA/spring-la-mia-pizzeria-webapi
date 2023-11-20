package com.experis.course.springPizzeriaCrud.exceptions;

public class PizzaNotFoundException extends RuntimeException {
    public PizzaNotFoundException(String message) {
        super(message);
    }
}
