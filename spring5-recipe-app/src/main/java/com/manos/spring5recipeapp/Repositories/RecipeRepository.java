package com.manos.spring5recipeapp.Repositories;

import com.manos.spring5recipeapp.models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe,Long> {
}
