package com.manos.spring5recipeapp.converters;

import com.manos.spring5recipeapp.commands.IngredientCommand;
import com.manos.spring5recipeapp.models.Ingredient;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngrentientCommand implements Converter<Ingredient,IngredientCommand> {

    @Autowired
    UomToUomCommand uomToUomCommand;

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if(ingredient == null){
            return null;
        }
        final IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setAmount(ingredient.getAmount());
        ingredientCommand.setDescription(ingredient.getDescription());
        ingredientCommand.setId(ingredient.getId());
        ingredientCommand.setUom(uomToUomCommand.convert(ingredient.getUom()));
        return ingredientCommand;
    }
}
