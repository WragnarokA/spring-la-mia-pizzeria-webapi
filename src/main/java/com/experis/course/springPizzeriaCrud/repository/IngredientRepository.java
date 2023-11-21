package com.experis.course.springPizzeriaCrud.repository;

import com.experis.course.springPizzeriaCrud.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    List<Ingredient> findByOrderByName();

    boolean existsByName(String name);
}
