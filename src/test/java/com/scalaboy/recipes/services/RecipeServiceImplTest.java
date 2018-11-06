package com.scalaboy.recipes.services;

import com.scalaboy.recipes.model.Recipe;
import com.scalaboy.recipes.repositories.RecipeRepository;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RecipeServiceImplTest {

    RecipeService recipeService;
    Recipe recipe;
    @Mock
    RecipeRepository recipeRepository;

    @SneakyThrows
    @Before
    public void setUp()  {
        recipe = new Recipe();
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipes() {
        //integration tests checking interaction between layesr]]
        when(recipeService.getRecipes()).thenReturn(new HashSet<Recipe>(){{add(recipe);}});
        recipeService.getRecipes();
        verify(recipeRepository, times(1)).findAll();

    }
}