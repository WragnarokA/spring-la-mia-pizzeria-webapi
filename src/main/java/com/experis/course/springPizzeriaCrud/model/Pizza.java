package com.experis.course.springPizzeriaCrud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pizze")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Name must not be blank")
    @Size(max = 255)
    @Size(min = 3)
    private String name;
    @Lob
    @NotBlank(message = "Description must not be blank")
    @Size(min = 3)
    private String description;
    @Lob
    @NotBlank(message = "Image must not be blank")
    @Size(min = 3)
    private String url;
    @DecimalMin("5.00")
    @DecimalMax("30.00")
    @NotNull
    private BigDecimal prezzo;
    
    @OneToMany(mappedBy = "pizza", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Discount> discounts = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Ingredient> ingredients;

    // GETTER AND SETTER


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
