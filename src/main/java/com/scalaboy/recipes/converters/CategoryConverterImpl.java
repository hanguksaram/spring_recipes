package com.scalaboy.recipes.converters;

import com.scalaboy.recipes.dtos.CategoryDto;
import com.scalaboy.recipes.model.Category;
import org.modelmapper.ModelMapper;

public class CategoryConverterImpl extends AbstractConverter<Category, CategoryDto> implements CategoryConverter {
    public CategoryConverterImpl(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public CategoryDto convertToDto(Category object) {
        return super.modelMapper.map(object, CategoryDto.class);
    }

    @Override
    public Category convertToPojo(CategoryDto object) {
        return super.modelMapper.map(object, Category.class);
    }
}
