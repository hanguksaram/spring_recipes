package com.scalaboy.recipes.controllers;

import com.scalaboy.recipes.model.Category;
import com.scalaboy.recipes.model.UnitOfMeasure;
import com.scalaboy.recipes.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @RequestMapping({"/", "", "/index", "/index.html"})
    public String index(Model model) {
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
