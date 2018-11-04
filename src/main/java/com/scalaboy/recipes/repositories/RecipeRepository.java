package com.scalaboy.recipes.repositories;

import com.scalaboy.recipes.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
