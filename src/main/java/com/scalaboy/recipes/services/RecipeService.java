package com.scalaboy.recipes.services;

import com.scalaboy.recipes.model.Recipe;

import java.util.Optional;
import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Optional<Recipe> findById(Long id);
}
