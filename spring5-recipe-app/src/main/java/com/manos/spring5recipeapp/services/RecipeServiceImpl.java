package com.manos.spring5recipeapp.services;

import com.manos.spring5recipeapp.Repositories.RecipeRepository;
import com.manos.spring5recipeapp.models.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    @Override
    public ArrayList<Recipe> getAllRecipes() {
        return (ArrayList<Recipe>) recipeRepository.findAll();
    }
}
