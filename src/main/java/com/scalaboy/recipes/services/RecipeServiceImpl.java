package com.scalaboy.recipes.services;

import com.scalaboy.recipes.model.Recipe;
import com.scalaboy.recipes.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        return StreamSupport.stream(recipeRepository.findAll().spliterator(), false).collect(Collectors.toCollection(TreeSet::new));
    }
}
