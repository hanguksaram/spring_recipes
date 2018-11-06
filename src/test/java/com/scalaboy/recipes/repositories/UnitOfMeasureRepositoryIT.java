package com.scalaboy.recipes.repositories;

import com.scalaboy.recipes.model.UnitOfMeasure;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByDescription() {
        Optional<String> unitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoon").map(u -> u.getDescription());
        assertEquals("Teaspoon", unitOfMeasure.get());
    }
    @Test(expected = NoSuchElementException.class)
    public void findByDescriptionNull() {
        Optional<String> unitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoonss").map(u -> u.getDescription());
        unitOfMeasure.get();
    }
}