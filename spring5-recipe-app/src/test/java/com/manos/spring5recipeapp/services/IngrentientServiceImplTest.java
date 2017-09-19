package com.manos.spring5recipeapp.services;

import com.manos.spring5recipeapp.Repositories.RecipeRepository;
import com.manos.spring5recipeapp.commands.IngredientCommand;
import com.manos.spring5recipeapp.converters.IngredientToIngrentientCommand;
import com.manos.spring5recipeapp.converters.UomToUomCommand;
import com.manos.spring5recipeapp.models.Ingredient;
import com.manos.spring5recipeapp.models.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class IngrentientServiceImplTest {


    IngrentientServiceImpl ingrentientService;

    @Mock
    RecipeRepository recipeRepository;


    IngredientToIngrentientCommand converter;
    UomToUomCommand uomToUomCommand = new UomToUomCommand();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingrentientService = new IngrentientServiceImpl();
        converter= new IngredientToIngrentientCommand();
        converter.uomToUomCommand=uomToUomCommand;
        ingrentientService.ingredientToIngrentientCommand=converter;
        ingrentientService.recipeRepository=recipeRepository;

    }

    @Test
    public void findByRecipeIdAndIngrentientId() throws Exception {
        Recipe  recipe = new Recipe();
        recipe.setId(1L);
        Ingredient ingredient=new Ingredient();
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(ingredient);
        ingredient.setId(1L);
        ingredient.setRecipe(recipe);
        recipe.setIngredients(ingredients);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        IngredientCommand ingredientCommand = ingrentientService.findByRecipeIdAndIngrentientId(1L, 1L);
        assertNotNull(ingredientCommand);
        assertEquals(ingredientCommand.getRecipeId(),Long.valueOf(1L));
        assertEquals(ingredientCommand.getId(),Long.valueOf(1L));
    }

}