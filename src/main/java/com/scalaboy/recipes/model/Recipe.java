package com.scalaboy.recipes.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Recipe implements Comparable<Recipe> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    @Lob
    private String direction;
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @Lob
    private Byte[] image;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    public Recipe(Long id) {
        this.id = id;
    }

    @Builder
    public Recipe(String description, Integer prepTime, Integer cookTime, Integer servings, String source, String url,
                  String direction, Difficulty difficulty, Set<Ingredient> ingredients, Set<Category> categories, Byte[] image, Notes notes) {
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.source = source;
        this.url = url;
        this.direction = direction;
        this.difficulty = difficulty;
        ingredients.forEach(i -> i.setRecipe(this));
        this.ingredients = ingredients;
        categories.forEach(c -> c.getRecipes().add(this));
        this.categories = categories;
        this.image = image;
        notes.setRecipe(this);
        this.notes = notes;
    }

    @Override
    public int compareTo(Recipe recipe) {
        if (recipe == null || recipe.id == null) return 1;
        return Long.compare(this.id, recipe.id);
    }
}
