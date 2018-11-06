package com.scalaboy.recipes.converters;

public interface Converter<T, R> {
    R convertToDto(T object);
    T convertToPojo(R object);
}
