package com.manos.spring5recipeapp.services;

import com.manos.spring5recipeapp.Repositories.RecipeRepository;
import com.manos.spring5recipeapp.commands.RecipeCommand;
import com.manos.spring5recipeapp.converters.RecipeCommandToRecipe;
import com.manos.spring5recipeapp.converters.RecipeToRecipeCommand;
import com.manos.spring5recipeapp.exceptions.NotFoundException;
import com.manos.spring5recipeapp.models.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;



    @Override
    public ArrayList<Recipe> getAllRecipes() {

        log.debug("i am in the service");
        return (ArrayList<Recipe>) recipeRepository.findAll();
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if(!recipe.isPresent()){
            throw new NotFoundException("recipe not found. For the ID value: " + id.toString());
        }
        return recipe.get();
    }

    @Override
    @Transactional
    public RecipeCommand save(RecipeCommand recipeCommand) {
        final Recipe recipe = recipeCommandToRecipe.convert(recipeCommand);
        Recipe returnRecipe = recipeRepository.save(recipe);

        return recipeToRecipeCommand.convert(returnRecipe);
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if(recipe.isPresent()){
            return recipeToRecipeCommand.convert(recipe.get());
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}
