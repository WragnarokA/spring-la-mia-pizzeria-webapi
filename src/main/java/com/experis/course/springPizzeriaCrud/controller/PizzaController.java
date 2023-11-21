package com.experis.course.springPizzeriaCrud.controller;

import com.experis.course.springPizzeriaCrud.exceptions.PizzaNotFoundException;
import com.experis.course.springPizzeriaCrud.model.Pizza;
import com.experis.course.springPizzeriaCrud.repository.PizzaRepository;
import com.experis.course.springPizzeriaCrud.service.IngredientService;
import com.experis.course.springPizzeriaCrud.service.PizzaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizze")
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public String index(@RequestParam Optional<String> search, Model model) {
        List<Pizza> pizzaList;
        model.addAttribute("pizzaList", pizzaService.getPizzaList(search));
        //model.addAttribute("searchKeyword", search.orElse(""));
        return "list";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Integer id, Model model) {
//        model.addAttribute("pizza", pizzaRepository.findById(id).orElseThrow(
//                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "pizza with id " + id + " not found")););
//        return "show";
        Optional<Pizza> result = pizzaRepository.findById(id);
        try {
            Pizza pizza = pizzaService.getPizzaById(id);
            model.addAttribute("pizza", pizza);
            return "show";
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }

    //******CREATE******///

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredientList", ingredientService.getAll());
        return "form";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredientList", ingredientService.getAll());
            return "form";
        }
        Pizza savePizza = pizzaRepository.save(formPizza);
        return "redirect:/pizze/show/" + savePizza.getId();
    }


    //******EDIT******///

    //metodo che mostra pgina di modifica della pizza
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        try {
            //Dal id recupero i dati  della pizza
            model.addAttribute("pizza", pizzaService.getPizzaById(id));
            model.addAttribute("ingredientList", ingredientService.getAll());

            return "/form";
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found");
        }

    }

    //Metodo che riceve il submit di Edit e lo salva
    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredientList", ingredientService.getAll());
            return "/pizze/form";
        }
        try {
            Pizza savePizza = pizzaService.editPizza(formPizza);
            return "redirect:/pizze/show/" + savePizza.getId();
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        //recupero la pizza che voglio modificare da DB
//        Pizza pizzaToEdit = pizzaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        //se lo trovo modifico puntualmente solo gli attributi che erano del form
//        pizzaToEdit.setName(formPizza.getName());
//        pizzaToEdit.setDescription(formPizza.getDescription());
//        pizzaToEdit.setUrl(formPizza.getUrl());
//        pizzaToEdit.setPrezzo(formPizza.getPrezzo());
        // se  non ci sono errori salvo la modifica della pizza

    }

    //******DELETE******///

    @PostMapping("/delete/{id}")
    private String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        // recupero la pizza con quell'id
        Pizza pizzaDelete = pizzaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        // se esiste la eliminiamo
        pizzaRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Pizza " + pizzaDelete.getName() + " delete!");
        return "redirect:/pizze";
    }
}



















