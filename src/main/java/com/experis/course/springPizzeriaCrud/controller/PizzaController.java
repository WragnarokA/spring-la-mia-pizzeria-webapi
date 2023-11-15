package com.experis.course.springPizzeriaCrud.controller;

import com.experis.course.springPizzeriaCrud.model.Pizza;
import com.experis.course.springPizzeriaCrud.repository.PizzaRepository;
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

    @GetMapping
    public String index(@RequestParam Optional<String> search, Model model) {
        List<Pizza> pizzaList;
        if (search.isPresent()) {
            pizzaList = pizzaRepository.findByNameContainingIgnoreCaseOrDescriptionContaining(search.get(), search.get());
        } else {
            pizzaList = pizzaRepository.findAll();
        }
        model.addAttribute("pizzaList", pizzaList);
        //model.addAttribute("searchKeyword", search.orElse(""));
        return "list";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Integer id, Model model) {
//        model.addAttribute("pizza", pizzaRepository.findById(id).orElseThrow(
//                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "pizza with id " + id + " not found")););
//        return "show";
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("pizza", result.get());
            return "show";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found");
        }
    }

    //******CREATE******///

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "form";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        Pizza savePizza = pizzaRepository.save(formPizza);
        return "redirect:/pizze/show/" + savePizza.getId();
    }


    //******EDIT******///

    //metodo che mostra pgina di modifica della pizza
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        //Dal id recupero i dati  della pizza
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("pizza", result.get());
            return "/form";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found");
        }
    }

    //Metodo che riceve il submit di Edit e lo salva
    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/pizze/form";
        }
        //recupero la pizza che voglio modificare da DB
        Pizza pizzaToEdit = pizzaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        //se lo trovo modifico puntualmente solo gli attributi che erano del form
        pizzaToEdit.setName(formPizza.getName());
        pizzaToEdit.setDescription(formPizza.getDescription());
        pizzaToEdit.setUrl(formPizza.getUrl());
        pizzaToEdit.setPrezzo(formPizza.getPrezzo());
        // se  non ci sono errori salvo la modifica della pizza
        Pizza savePizza = pizzaRepository.save(pizzaToEdit);
        return "redirect:/pizze/show/" + savePizza.getId();

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



















