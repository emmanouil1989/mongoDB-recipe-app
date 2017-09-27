package com.manos.spring5recipeapp.services;

import com.manos.spring5recipeapp.Repositories.RecipeRepository;
import com.manos.spring5recipeapp.Repositories.UnitOfMeasureRepository;
import com.manos.spring5recipeapp.commands.IngredientCommand;
import com.manos.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.manos.spring5recipeapp.converters.IngredientCommandToIngredient;
import com.manos.spring5recipeapp.converters.IngredientToIngrentientCommand;
import com.manos.spring5recipeapp.converters.UomCommandToUom;
import com.manos.spring5recipeapp.converters.UomToUomCommand;
import com.manos.spring5recipeapp.models.Ingredient;
import com.manos.spring5recipeapp.models.Recipe;
import com.manos.spring5recipeapp.models.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IngrentientServiceImplTest {



    IngrentientServiceImpl ingrentientService;

    @Mock
    RecipeRepository recipeRepository;


    IngredientToIngrentientCommand converter;
    UomToUomCommand uomToUomCommand = new UomToUomCommand();
    IngredientCommandToIngredient ingredientCommandToIngredient = new IngredientCommandToIngredient();
    UomCommandToUom uomCommandToUom = new UomCommandToUom();
    @Mock
    UnitOfMeasureRepository uomRepo;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingrentientService = new IngrentientServiceImpl();
        converter= new IngredientToIngrentientCommand();
        converter.uomToUomCommand=uomToUomCommand;
        ingrentientService.ingredientToIngrentientCommand=converter;
        ingrentientService.recipeRepository=recipeRepository;
        ingrentientService.repo=uomRepo;
        ingredientCommandToIngredient.uomCommandToUom=uomCommandToUom;
        ingrentientService.ingredientCommandToIngredient=ingredientCommandToIngredient;

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

    @Test
    public void saveIngredient() throws Exception {

        IngredientCommand ingredientCommand  = new IngredientCommand();
        ingredientCommand.setDescription("manos");
        ingredientCommand.setAmount(BigDecimal.ONE);
        ingredientCommand.setRecipeId(1L);
        UnitOfMeasureCommand unit = new UnitOfMeasureCommand();
        unit.setId(1L);
        unit.setDescription("manos uom");
        ingredientCommand.setId(1L);
        ingredientCommand.setUom(unit);


        Recipe  recipe = new Recipe();
        recipe.setId(1L);
        Ingredient ingredient=new Ingredient();
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(ingredient);
        ingredient.setId(1L);
        ingredient.setRecipe(recipe);
        UnitOfMeasure manos = new UnitOfMeasure();
        manos.setDescription("manos");
        manos.setId(1L);
        ingredient.setUom(manos);
        recipe.setIngredients(ingredients);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId(1l);
        uom2.setDescription("malaka");

        Optional<UnitOfMeasure> uom = Optional.of(uom2);
        when(uomRepo.findById(anyLong())).thenReturn(uom);

        when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe);

        IngredientCommand command = ingrentientService.saveIngredient(ingredientCommand);
        assertNotNull(command);

    }


    @Test
    public void deleteIngredient() throws Exception {

        //given
        Recipe recipe = new Recipe();
        Ingredient ingredient = new Ingredient();
        ingredient.setId(3L);
        ingredient.setRecipe(recipe);
        recipe.getIngredients().add(ingredient);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //when
        ingrentientService.deleteIngredient(1L, 3L);

        //then
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }

}