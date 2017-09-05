package com.manos.spring5recipeapp.Repositories;

import com.manos.spring5recipeapp.models.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long> {

    public Optional<UnitOfMeasure> findByDescription(String description);
}
