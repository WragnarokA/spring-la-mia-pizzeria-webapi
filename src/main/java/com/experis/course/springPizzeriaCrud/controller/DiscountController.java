package com.experis.course.springPizzeriaCrud.controller;

import com.experis.course.springPizzeriaCrud.exceptions.DiscountNotFountException;
import com.experis.course.springPizzeriaCrud.exceptions.PizzaNotFoundException;
import com.experis.course.springPizzeriaCrud.model.Discount;
import com.experis.course.springPizzeriaCrud.service.DiscountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/discounts")
public class DiscountController {
    @Autowired
    DiscountService discountService;

    @GetMapping("/create")
    public String create(@RequestParam Integer pizzaId, Model model) {

        try {
            model.addAttribute("discount", discountService.createNewDiscount(pizzaId));
            return "discounts/form";
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/create")
    public String doCreate(@Valid Discount formDiscount, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "discounts/form";
        }
        Discount saveDiscount = discountService.saveDiscount(formDiscount);

        return "redirect:/pizze/show/" + formDiscount.getPizza().getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        try {
            Discount discount = discountService.getDiscount(id);
            model.addAttribute("discount", discount);
            return "discounts/form";
        } catch (DiscountNotFountException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("discount") Discount formDiscount, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "discounts/form";
        }
        Discount saveDiscount = discountService.saveDiscount(formDiscount);
        return "redirect:/pizze/show/" + formDiscount.getPizza().getId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            Discount discountToDelete = discountService.getDiscount(id);
            redirectAttributes.addFlashAttribute("message", "Discount delete");
            discountService.deleteDiscount(discountToDelete);
            return "redirect:/pizze/show/" + discountToDelete.getPizza().getId();
        } catch (DiscountNotFountException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
