package com.scalaboy.recipes.controllers;

import com.scalaboy.recipes.model.Category;
import com.scalaboy.recipes.model.UnitOfMeasure;
import com.scalaboy.recipes.repositories.CategoryRepository;
import com.scalaboy.recipes.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"/", "", "/index", "/index.html"})
    public String index() {
        Optional<Category> categoryOptional = categoryRepository.findByDescription("Americn");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspon");
        System.out.printf("Cat Id is: %s uom is: %s ", categoryOptional.map(x -> x.getDescription()).orElse("No such category"), unitOfMeasureOptional.map(x -> x.getDescription()).orElse("No such uom"));
        return "index";
    }
}
