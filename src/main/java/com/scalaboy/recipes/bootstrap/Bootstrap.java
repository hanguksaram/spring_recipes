package com.scalaboy.recipes.bootstrap;

import com.scalaboy.recipes.model.*;
import com.scalaboy.recipes.repositories.CategoryRepository;
import com.scalaboy.recipes.repositories.RecipeRepository;
import com.scalaboy.recipes.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final List<UnitOfMeasure> unitOfMeasures = new ArrayList<>();

    public Bootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>(2);
        Set<UnitOfMeasure> unitOfMeasures = StreamSupport.stream(unitOfMeasureRepository.findAll()
                .spliterator(), false).collect(Collectors.toSet());

        Category americanCategoryOptional = categoryRepository.findByDescription("American").orElseGet(() -> categoryRepository.findByDescription("Default").get());
        Category mexicanCategoryOptional = categoryRepository.findByDescription("Mexican").orElseGet(() -> categoryRepository.findByDescription("Default").get());

        Recipe guaRecipe = Recipe.builder().description("Perfect Guacamole").prepTime(10).cookTime(0)
                .difficulty(Difficulty.EASY).direction(
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris" + "\n"
                        + " nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur." + "\n"
                        + "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
                ).build();
        Recipe kartohaRecipe = Recipe.builder().description("Kartoshechka").prepTime(10).cookTime(0)
                .difficulty(Difficulty.EASY).direction(
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris" + "\n"
                                + " nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur." + "\n"
                                + "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
                ).build();

        Ingredient ing1 = new Ingredient("random1", new BigDecimal(2), unitOfMeasures.stream().filter(x -> x.getDescription().equals("Teaspoon")).findFirst().get());
        Ingredient ing2 = new Ingredient("random2", new BigDecimal(3), unitOfMeasures.stream().filter(x -> x.getDescription().equals("Cup")).findFirst().get());

        Notes guacNotes = new Notes();
        Notes kartohaNotes = new Notes();

        guacNotes.setRecipeNotes("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris");
        kartohaNotes.setRecipeNotes("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris");
        guacNotes.setRecipe(guaRecipe);
        kartohaNotes.setRecipe(kartohaRecipe);
        ing1.setRecipe(guaRecipe);
        ing2.setRecipe(kartohaRecipe);
        americanCategoryOptional.setRecipes(new HashSet<>(Arrays.asList(kartohaRecipe)));
        mexicanCategoryOptional.setRecipes(new HashSet<>(Arrays.asList(guaRecipe)));
        guaRecipe.setNotes(guacNotes);
        kartohaRecipe.setNotes(kartohaNotes);
        guaRecipe.setIngredients(new HashSet<>(Arrays.asList(ing1)));
        kartohaRecipe.setIngredients(new HashSet<>(Arrays.asList(ing2)));
        guaRecipe.setCategories(new HashSet<>(Arrays.asList(mexicanCategoryOptional)));
        kartohaRecipe.setCategories(new HashSet<>(Arrays.asList(americanCategoryOptional)));

        recipes.add(guaRecipe);
        recipes.add(kartohaRecipe);

        return recipes;
    }


}
