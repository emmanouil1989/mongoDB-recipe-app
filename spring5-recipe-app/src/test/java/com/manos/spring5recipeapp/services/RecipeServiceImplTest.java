package com.manos.spring5recipeapp.services;

import com.manos.spring5recipeapp.Repositories.RecipeRepository;
import com.manos.spring5recipeapp.models.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class RecipeServiceImplTest {


    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl();
        recipeService.recipeRepository=recipeRepository;
    }

    @Test
    public void getAllRecipes() throws Exception {

        Recipe recipe = new Recipe();
        recipe.setCookTime(1);
        recipe.setDescription("sf");
        ArrayList<Recipe> recipeList = new ArrayList();
        recipeList.add(recipe);
        when(recipeService.getAllRecipes()).thenReturn(recipeList);
        ArrayList<Recipe> allRecipes = recipeService.getAllRecipes();

        assertEquals(allRecipes.size(),1);
    }

    @Test
    public void testById() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        Recipe recipeServiceById = recipeService.findById(1L);
        assertNotNull(recipeServiceById);
    }
}