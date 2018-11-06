package com.scalaboy.recipes.services;

import com.scalaboy.recipes.model.Recipe;
import com.scalaboy.recipes.repositories.RecipeRepository;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

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
    public void getRecipeById() {
        Optional<Recipe> recipe = Optional.of(new Recipe(1L));
        when(this.recipeRepository.findById(anyLong())).thenReturn(recipe);

        Optional<Recipe> recipeReturned = this.recipeService.findById(1L);
        assertNotNull("recipe is null", recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void getRecipes() {
        //integration tests checking interaction between layesr]]
        when(recipeRepository.findAll()).thenReturn(new HashSet<Recipe>(){{add(recipe);}});
        recipeService.getRecipes();
        verify(recipeRepository, times(1)).findAll();

    }
}