package com.scalaboy.recipes.converters;

import org.modelmapper.ModelMapper;

public abstract class AbstractConverter<T, R> implements Converter<T, R> {

    protected final ModelMapper modelMapper;
    public AbstractConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    //опять сталкиваюсь со стиранием типа дженериков в рантайме =((
    //маррер требует джава.ланг.класс<T> общую реализации сделать не получится((
   // @Override
    //public R convertToDto(T object) {
    //    R dto = modelMapper.map(object, getClass(R));
    //}

    //@Override
    //public T convertToPojo(R object) {
    //    return null;
    //}
}
