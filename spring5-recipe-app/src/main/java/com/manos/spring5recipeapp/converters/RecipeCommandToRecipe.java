package com.manos.spring5recipeapp.converters;

import com.manos.spring5recipeapp.commands.CategoryCommand;
import com.manos.spring5recipeapp.commands.IngredientCommand;
import com.manos.spring5recipeapp.commands.NotesCommand;
import com.manos.spring5recipeapp.commands.RecipeCommand;
import com.manos.spring5recipeapp.models.Category;
import com.manos.spring5recipeapp.models.Ingredient;
import com.manos.spring5recipeapp.models.Notes;
import com.manos.spring5recipeapp.models.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.util.HashSet;
import java.util.Set;

public class RecipeCommandToRecipe implements Converter<RecipeCommand,Recipe> {

    @Autowired
    CategoryCommandToCategory categoryCommandToCategory;

    @Autowired
    IngredientCommandToIngredient ingredientCommandToIngredient;

    @Autowired
    NoteCommandToNote noteCommandToNote;

    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {

        if(recipeCommand == null){
            return null;
        }
        final Recipe recipe = new Recipe();
        recipe.setDescription(recipeCommand.getDescription());
        recipe.setId(recipeCommand.getId());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setDifficulty(recipeCommand.getDifficulty());
        recipe.setDirections(recipeCommand.getDirections());
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setServings(recipeCommand.getServings());
        Set<CategoryCommand> recipeCommands =  recipeCommand.getCategories();
        Set<Category> categories =  new HashSet<>();
        if (recipeCommand.getCategories() != null && recipeCommand.getCategories().size() > 0){
            recipeCommand.getCategories()
                    .forEach( category -> recipe.getCategories().add(categoryCommandToCategory.convert(category)));
        }

        if (recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0){
            recipeCommand.getIngredients()
                    .forEach(ingredient -> recipe.getIngredients().add(ingredientCommandToIngredient.convert(ingredient)));
        }
        recipe.setRecipeNotes(noteCommandToNote.convert(recipeCommand.getNotes()));
        recipe.setSource(recipeCommand.getSource());
        recipe.setUrl(recipeCommand.getUrl());
        return recipe;
    }
}
