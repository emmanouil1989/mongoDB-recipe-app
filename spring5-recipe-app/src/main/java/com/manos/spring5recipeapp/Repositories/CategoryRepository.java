package com.manos.spring5recipeapp.Repositories;

import com.manos.spring5recipeapp.models.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category,Long> {

    public Optional<Category> findByDescription(String description);
}
