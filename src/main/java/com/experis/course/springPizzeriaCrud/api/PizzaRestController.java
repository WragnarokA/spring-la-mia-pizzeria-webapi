package com.experis.course.springPizzeriaCrud.api;

import com.experis.course.springPizzeriaCrud.exceptions.PizzaNotFoundException;
import com.experis.course.springPizzeriaCrud.model.Pizza;
import com.experis.course.springPizzeriaCrud.service.PizzaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pizze")
@CrossOrigin
public class PizzaRestController {

    @Autowired
    private PizzaService pizzaService;

    //endpoint per la lista di tutte le pizze
    @GetMapping
    public List<Pizza> index(@RequestParam Optional<String> search) {
        return pizzaService.getPizzaList(search);
    }

    //endpoint per i dettagli della pizza presso dal id
    @GetMapping("/{id}")
    public Pizza details(@PathVariable Integer id) {
        try {
            return pizzaService.getPizzaById(id);
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    //endpoint per creare una nuova pizza
    @PostMapping
    public Pizza create(@Valid @RequestBody Pizza pizza) {
        pizza.setId(null);
        try {
            return pizzaService.createPizza(pizza);
        } catch (Exception e) {
            // Puoi gestire l'eccezione qui se necessario
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    //endpoint per modificare una pizza
    @PutMapping("/{id}")
    public Pizza edit(@PathVariable Integer id, @Valid @RequestBody Pizza pizza) {
        pizza.setId(id);
        try {
            return pizzaService.editPizza(pizza);
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    //endpoint per la Delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            Pizza pizzaToDelete = pizzaService.getPizzaById(id);
            pizzaService.deletePizzaById(id);
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    //endpoint per la paginazione
    @GetMapping("/page/v2")
    public Page<Pizza> pagedIndexV2(@PageableDefault(page = 0, size = 2) Pageable pageable) {
        return pizzaService.getPage(pageable);
    }
}
