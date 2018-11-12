package com.scalaboy.recipes.services

import scala.collection.JavaConverters._
import com.scalaboy.recipes.model.Recipe
import com.scalaboy.recipes.repositories.RecipeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RecipeScalaServiceImpl(@Autowired private val recipeRepository: RecipeRepository) extends RecipeScalaService {
  override def getRecipes: List[Option[Recipe]] = recipeRepository.findAll().asScala.map(x => Option(x)).toList
}
