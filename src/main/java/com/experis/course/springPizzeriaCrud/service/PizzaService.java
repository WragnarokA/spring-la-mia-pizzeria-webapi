package com.experis.course.springPizzeriaCrud.service;

import com.experis.course.springPizzeriaCrud.exceptions.PizzaNotFoundException;
import com.experis.course.springPizzeriaCrud.model.Pizza;
import com.experis.course.springPizzeriaCrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    //metodo che restituisce tutte le pizze
    public List<Pizza> getPizzaList(Optional<String> search) {

        if (search.isPresent()) {
            return pizzaRepository.findByNameContainingIgnoreCaseOrDescriptionContaining(search.get(), search.get());
        } else {
            return pizzaRepository.findAll();
        }
    }

    public List<Pizza> getPizzaList() {
        return pizzaRepository.findAll();
    }

    // metodo che restituisce una pizza se non la trova solleva un'eccezione
    public Pizza getPizzaById(Integer id) throws PizzaNotFoundException {
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new PizzaNotFoundException("Pizza with " + id + " not found");
        }
    }

    // metodo per creare una pizza
    public Pizza createPizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }


    // Metodo per modificare un nuova pizza

    public Pizza editPizza(Pizza pizza) throws PizzaNotFoundException {
        Pizza pizzaToEdit = getPizzaById(pizza.getId());
        pizzaToEdit.setName(pizza.getName());
        pizzaToEdit.setDescription(pizza.getDescription());
        pizzaToEdit.setUrl(pizza.getUrl());
        pizzaToEdit.setPrezzo(pizza.getPrezzo());
        pizzaToEdit.setIngredients(pizza.getIngredients());

        return pizzaRepository.save(pizzaToEdit);
    }


}












