package com.manos.spring5recipeapp.converters;

        import com.manos.spring5recipeapp.commands.RecipeCommand;
        import com.manos.spring5recipeapp.models.Recipe;
        import lombok.Synchronized;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.core.convert.converter.Converter;
        import org.springframework.lang.Nullable;
        import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe,RecipeCommand> {

    @Autowired
    CategoryToCategoryCommand categoryToCategoryCommand;

    @Autowired
    NotesToNotesCommand notesToNotesCommand;

    @Autowired
    IngredientToIngrentientCommand  ingredientToIngrentientCommand;

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {
        if(recipe == null) {
            return null;
        }
        final RecipeCommand recipeCommand = new RecipeCommand();
        if(recipe.getCategories() != null && recipe.getCategories().size() >0) {
            recipe.getCategories().forEach(category -> recipeCommand.getCategories().add(categoryToCategoryCommand.convert(category)));
        }
        recipeCommand.setCookTime(recipe.getCookTime());
        recipeCommand.setDescription(recipe.getDescription());
        recipeCommand.setDifficulty(recipe.getDifficulty());
        recipeCommand.setDirections(recipe.getDirections());
        recipeCommand.setId(recipe.getId());
        recipeCommand.setNotes(notesToNotesCommand.convert(recipe.getRecipeNotes()));
        if(recipe.getIngredients() != null && recipe.getIngredients().size()>0){
            recipe.getIngredients().forEach(ingredient ->recipeCommand.getIngredients().add(ingredientToIngrentientCommand.convert(ingredient)));
        }
        recipeCommand.setPrepTime(recipe.getPrepTime());
        recipeCommand.setServings(recipe.getServings());
        recipeCommand.setSource(recipe.getSource());
        recipeCommand.setUrl(recipe.getUrl());

        return recipeCommand;
    }
}
