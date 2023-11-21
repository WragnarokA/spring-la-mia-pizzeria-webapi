package com.experis.course.springPizzeriaCrud.service;

import com.experis.course.springPizzeriaCrud.exceptions.IngredientNameUniqueException;
import com.experis.course.springPizzeriaCrud.model.Ingredient;
import com.experis.course.springPizzeriaCrud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> getAll() {
        return ingredientRepository.findByOrderByName();
    }

    public Ingredient save(Ingredient ingredient) throws IngredientNameUniqueException {
        //vefico che non esista
        if (ingredientRepository.existsByName(ingredient.getName())) {
            throw new IngredientNameUniqueException(ingredient.getName());
        }
        //trasformo il nome in LowerCase
        ingredient.setName(ingredient.getName().toLowerCase());
        //salvo su DB
        return ingredientRepository.save(ingredient);
    }
}
