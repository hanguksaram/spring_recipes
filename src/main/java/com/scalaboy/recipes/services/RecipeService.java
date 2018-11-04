package com.scalaboy.recipes.services;

import com.scalaboy.recipes.model.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}
