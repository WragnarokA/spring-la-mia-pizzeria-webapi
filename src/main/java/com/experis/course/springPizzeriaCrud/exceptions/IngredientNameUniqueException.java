package com.experis.course.springPizzeriaCrud.exceptions;

public class IngredientNameUniqueException extends RuntimeException {
    public IngredientNameUniqueException(String message) {
        super(message);
    }
}
