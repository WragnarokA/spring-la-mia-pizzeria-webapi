package com.experis.course.springPizzeriaCrud.controller;

import com.experis.course.springPizzeriaCrud.exceptions.IngredientNameUniqueException;
import com.experis.course.springPizzeriaCrud.model.Ingredient;
import com.experis.course.springPizzeriaCrud.service.IngredientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    IngredientService ingredientService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("ingredientsList", ingredientService.getAll());
        model.addAttribute("ingredientObj", new Ingredient());
        return "ingredients/index";

    }

    @PostMapping
    public String doSave(@Valid @ModelAttribute("ingredientObj") Ingredient formIngredient, BindingResult bindingResult, Model model) {
        //valido l'ingrediente
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredientsList", ingredientService.getAll());
            return "ingredients/index";
        }
        //salvo il nuovo ingrediente
        try {
            ingredientService.save(formIngredient);
            return "redirect:/ingredients";
        } catch (IngredientNameUniqueException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A ingredient with name " + e.getMessage() + " already exists");
        }
    }
}
