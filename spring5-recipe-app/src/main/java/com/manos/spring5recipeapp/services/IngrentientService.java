package com.manos.spring5recipeapp.services;

import com.manos.spring5recipeapp.commands.IngredientCommand;

public interface IngrentientService {

    IngredientCommand findByRecipeIdAndIngrentientId(Long recipeId,Long ingrentientId);

    IngredientCommand saveIngredient(IngredientCommand command);
}
