package com.experis.course.springPizzeriaCrud.service;

import com.experis.course.springPizzeriaCrud.model.Ingredient;
import com.experis.course.springPizzeriaCrud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    private List<Ingredient> getAll() {
        return ingredientRepository.findByOrderByName();
    }
}
