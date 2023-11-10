package com.experis.course.springPizzeriaCrud.repository;

import com.experis.course.springPizzeriaCrud.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

}
