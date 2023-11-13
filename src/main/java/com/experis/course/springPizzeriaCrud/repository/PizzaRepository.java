package com.experis.course.springPizzeriaCrud.repository;

import com.experis.course.springPizzeriaCrud.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    List<Pizza> findByNameContainingIgnoreCaseOrDescriptionContaining(String nameKeyword, String descriptionKeyword);

}
