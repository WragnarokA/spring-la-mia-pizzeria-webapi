package com.experis.course.springPizzeriaCrud.service;

import com.experis.course.springPizzeriaCrud.exceptions.DiscountNotFountException;
import com.experis.course.springPizzeriaCrud.exceptions.PizzaNotFoundException;
import com.experis.course.springPizzeriaCrud.model.Discount;
import com.experis.course.springPizzeriaCrud.model.Pizza;
import com.experis.course.springPizzeriaCrud.repository.DiscountRepository;
import com.experis.course.springPizzeriaCrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DiscountService {
    @Autowired
    PizzaRepository pizzaRepository;
    @Autowired
    DiscountRepository discountRepository;

    public Discount createNewDiscount(Integer pizzaID) throws PizzaNotFoundException {
        Pizza pizza = pizzaRepository.findById(pizzaID).orElseThrow(() -> new PizzaNotFoundException("Pizza with id " + pizzaID + " not fount"));
        Discount discount = new Discount();
        discount.setStartDate(LocalDate.now());
        discount.setExpireDate(LocalDate.now().plusMonths(1));
        discount.setPizza(pizza);
        return discount;
    }

    public Discount saveDiscount(Discount discount) {
        return discountRepository.save(discount);

    }

    public Discount getDiscount(Integer id) throws DiscountNotFountException {
        return discountRepository.findById(id)
                .orElseThrow(() -> new DiscountNotFountException("Discount with id " + "not found"));
    }

    public void deleteDiscount(Discount discount) {
        discountRepository.delete(discount);
    }
}
