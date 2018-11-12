package com.scalaboy.recipes.services

import com.scalaboy.recipes.model.Recipe

trait RecipeScalaService {
  def getRecipes: List[Option[Recipe]]
}
