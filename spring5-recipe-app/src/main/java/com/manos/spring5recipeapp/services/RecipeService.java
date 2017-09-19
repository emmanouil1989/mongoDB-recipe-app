package com.manos.spring5recipeapp.services;


import com.manos.spring5recipeapp.commands.RecipeCommand;
import com.manos.spring5recipeapp.models.Recipe;

import java.util.ArrayList;


public interface RecipeService {
     ArrayList<Recipe> getAllRecipes();
     Recipe findById(Long id);
     RecipeCommand save(RecipeCommand recipeCommand);
     RecipeCommand findCommandById(Long id);
     void deleteById (Long id);

}

