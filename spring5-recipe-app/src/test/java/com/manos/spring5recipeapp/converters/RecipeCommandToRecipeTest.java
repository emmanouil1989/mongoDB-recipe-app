package com.manos.spring5recipeapp.converters;

import com.manos.spring5recipeapp.commands.*;
import com.manos.spring5recipeapp.models.Difficulty;
import com.manos.spring5recipeapp.models.Ingredient;
import com.manos.spring5recipeapp.models.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

    public static final String URL = "url";
    public static final String SOURCE = "source";
    public static final Long ID = 1L;
    public static final Integer PREP_TIME = 2;
    public static final Integer SERVINGS = 1;
    public static final String DESCRIPTION = "desc";
    public static final String DIRECTIONS = "directions";
    public static final Integer COOK_TIME = 1;
    public static final BigDecimal AMOUNT = BigDecimal.ONE;
    RecipeCommandToRecipe recipeCommandToRecipe;


    CategoryCommandToCategory categoryCommandToCategory = new CategoryCommandToCategory();


    IngredientCommandToIngredient ingredientCommandToIngredient = new IngredientCommandToIngredient();

    UomCommandToUom uomCommandToUom = new UomCommandToUom();


    NoteCommandToNote noteCommandToNote = new NoteCommandToNote();

    @Before
    public void setUp() throws Exception {
        recipeCommandToRecipe = new RecipeCommandToRecipe();
        recipeCommandToRecipe.categoryCommandToCategory=categoryCommandToCategory;
        ingredientCommandToIngredient.uomCommandToUom=uomCommandToUom;
        recipeCommandToRecipe.ingredientCommandToIngredient=ingredientCommandToIngredient;
        recipeCommandToRecipe.noteCommandToNote=noteCommandToNote;
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(recipeCommandToRecipe.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(recipeCommandToRecipe.convert(new RecipeCommand()));
    }

    @Test
    public void convertTest() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setUrl(URL);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setDifficulty(Difficulty.EASY);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setCookTime(COOK_TIME);

        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID);
        categoryCommand.setDescription(DESCRIPTION);
        recipeCommand.getCategories().add(categoryCommand);

        IngredientCommand ingredient = new IngredientCommand();
        ingredient.setId(ID);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);

        UnitOfMeasureCommand  unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(ID);
        unitOfMeasureCommand.setDescription(DESCRIPTION);

        ingredient.setUnitOfMeasure(unitOfMeasureCommand);
        recipeCommand.getIngredients().add(ingredient);

        NotesCommand notes = new NotesCommand();
        notes.setId(ID);
        notes.setRecipeNotes(DESCRIPTION);
        recipeCommand.setNotes(notes);
        recipeCommand.setId(ID);
        Recipe recipe = recipeCommandToRecipe.convert(recipeCommand);

        assertNotNull(recipe);
        assertEquals(ID, recipe.getId());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(Difficulty.EASY, recipe.getDifficulty());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(ID, recipe.getRecipeNotes().getId());
        assertEquals(1, recipe.getCategories().size());
        assertEquals(1, recipe.getIngredients().size());

    }

}