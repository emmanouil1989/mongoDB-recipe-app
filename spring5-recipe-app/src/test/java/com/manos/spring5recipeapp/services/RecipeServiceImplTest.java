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

import static org.junit.Assert.*;

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
        Mockito.when(recipeService.getAllRecipes()).thenReturn(recipeList);
        ArrayList<Recipe> allRecipes = recipeService.getAllRecipes();

        assertEquals(allRecipes.size(),1);
    }

}