package com.manos.spring5recipeapp.services;

import com.manos.spring5recipeapp.Repositories.RecipeRepository;
import com.manos.spring5recipeapp.commands.IngredientCommand;
import com.manos.spring5recipeapp.converters.IngredientToIngrentientCommand;
import com.manos.spring5recipeapp.models.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class IngrentientServiceImpl implements IngrentientService {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    IngredientToIngrentientCommand ingredientToIngrentientCommand;


    @Override
    public IngredientCommand findByRecipeIdAndIngrentientId(Long recipeId, Long ingrentientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(!recipeOptional.isPresent()){
            log.error("there is no recipeOptional with that id");
        }

        Recipe recipe = recipeOptional.get();
        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingrentientId))
                .map(ingredient -> ingredientToIngrentientCommand.convert(ingredient)).findFirst();

        if(!ingredientCommandOptional.isPresent()){
            log.error("ingredient is not present with that id");
        }
        return ingredientCommandOptional.get();
    }
}
