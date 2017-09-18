package com.manos.spring5recipeapp.converters;

import com.manos.spring5recipeapp.commands.IngredientCommand;
import com.manos.spring5recipeapp.models.Ingredient;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand,Ingredient> {

    @Autowired
    UomCommandToUom uomCommandToUom;

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand ingredientCommand) {
        if(ingredientCommand == null){
            return  null;
        }
        final Ingredient ingredient = new Ingredient();
        ingredient.setAmount(ingredientCommand.getAmount());
        ingredient.setDescription(ingredientCommand.getDescription());
        ingredient.setId(ingredientCommand.getId());
        ingredient.setUom(uomCommandToUom.convert(ingredientCommand.getUom()));
        return ingredient;
    }
}
