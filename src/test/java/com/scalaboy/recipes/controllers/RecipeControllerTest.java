package com.scalaboy.recipes.controllers;

import com.scalaboy.recipes.model.Recipe;
import com.scalaboy.recipes.services.RecipeService;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class RecipeControllerTest {

    RecipeController controller;

    @Mock
    RecipeService service;

    MockMvc mockMvc;

    @Mock
    Model model;

    @Mock
    HttpServletResponse response;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new RecipeController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();


    }
    @SneakyThrows
    @Test
    public void getProperRecipePage() {
        when(service.findById(1L)).thenReturn(Optional.of(new Recipe(1L)));
        mockMvc.perform(get("/recipe/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/index"));

    }
    @SneakyThrows
    @Test
    public void getRecipePageWithBadQueryResource() {
        mockMvc.perform(get("/recipe/show/feqwf"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("exceptionPages/badRequest"));

    }
    @SneakyThrows
    @Test
    public void getRecipePageWithNoExistResource() {
        mockMvc.perform(get("/recipe/show/-1"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("exceptionPages/notFound"));

    }

    @Test
    public void getRecipe() {
        when(service.findById(anyLong())).thenReturn(Optional.of(new Recipe(1L)));
        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        String view = this.controller.getRecipe("1", model, response);

        assertEquals("recipe/index", view);
        verify(service, times(1)).findById(anyLong());
        verify(model, times(1)).addAttribute(eq("recipe"), argumentCaptor.capture());
        Recipe recipeInController = argumentCaptor.getValue();
        assertEquals((Long)1L, recipeInController.getId());

    }
}