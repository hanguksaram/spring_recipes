package com.scalaboy.recipes.contollers

import com.scalaboy.recipes.model.Recipe
import com.scalaboy.recipes.services.RecipeScalaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
@Controller
class RecipeScalaController(@Autowired private val recipeScalaService: RecipeScalaService)
{
  @GetMapping(path = Array("/lol"))
  def home(model: Model): String = {
    val recipes: List[Recipe] = recipeScalaService.getRecipes.collect {case Some(recipe) => recipe}
    model.addAttribute("recipes", recipes )
    "index"
  }
}