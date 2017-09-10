package com.manos.spring5recipeapp.controllers;

import com.manos.spring5recipeapp.models.Recipe;
import com.manos.spring5recipeapp.services.RecipeService;
import com.manos.spring5recipeapp.services.RecipeServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    IndexController indexController;

    @Mock
    Model model;

    @Mock
    RecipeServiceImpl recipeService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController();
        indexController.recipeService=recipeService;

    }

    @Test
    public void testWithMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getAllRecipes() throws Exception {
        ArrayList<Recipe> recipes = new ArrayList<>();
        Recipe recipe = new Recipe();
        recipe.setDescription("manos");
        recipes.add(recipe);
        Mockito.when(model.addAttribute(Mockito.any(String.class))).thenReturn(model);
        Mockito.when(recipeService.getAllRecipes()).thenReturn(recipes);
        String allRecipes = indexController.getAllRecipes(model);
        assertEquals("index",allRecipes);


    }

}