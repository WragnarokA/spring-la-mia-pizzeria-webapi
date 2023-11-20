package com.experis.course.springPizzeriaCrud.repository;

import com.experis.course.springPizzeriaCrud.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {
}
