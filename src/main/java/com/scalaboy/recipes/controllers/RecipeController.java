package com.scalaboy.recipes.controllers;

import com.scalaboy.recipes.model.Recipe;
import com.scalaboy.recipes.services.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @RequestMapping("/show/{id}")
    public String getRecipe(@PathVariable String id, Model model, HttpServletResponse response) {
        Long recipeId;
        try {
             recipeId = Long.valueOf(id);
        }
        catch (NumberFormatException ex) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return "exceptionPages/badRequest";
        }
        Optional<Recipe> recipeOptional = this.recipeService.findById(recipeId);
        if (recipeOptional.isPresent()) {
            model.addAttribute("recipe", recipeOptional.get());
            return "recipe/index";
        }
        else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return "exceptionPages/notFound";
        }


    }
}
