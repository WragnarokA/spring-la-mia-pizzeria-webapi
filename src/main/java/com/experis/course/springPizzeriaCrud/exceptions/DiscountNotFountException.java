package com.experis.course.springPizzeriaCrud.exceptions;

public class DiscountNotFountException extends RuntimeException {
    public DiscountNotFountException(String message) {
        super(message);
    }
}
