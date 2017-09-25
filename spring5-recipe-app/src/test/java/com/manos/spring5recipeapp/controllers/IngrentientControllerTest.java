package com.manos.spring5recipeapp.controllers;

import com.manos.spring5recipeapp.commands.IngredientCommand;
import com.manos.spring5recipeapp.commands.RecipeCommand;
import com.manos.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.manos.spring5recipeapp.services.IngrentientService;
import com.manos.spring5recipeapp.services.RecipeService;
import com.manos.spring5recipeapp.services.UnitOfMeasureService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IngrentientControllerTest {



    IngrentientController ingrentientController;

    @Mock
    RecipeService recipeService;

    @Mock
    IngrentientService ingrentientService;

    MockMvc mockMvc;

    @Mock
    UnitOfMeasureService uomService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingrentientController = new IngrentientController();
        ingrentientController.recipeService=recipeService;
        ingrentientController.ingrentientService=ingrentientService;
        ingrentientController.unitOfMeasureService=uomService;
        mockMvc = MockMvcBuilders.standaloneSetup(ingrentientController).build();
    }

    @Test
    public void listOfIngrentients() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
        mockMvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("recipe")).andExpect(view().name("recipe/ingredient/list"));
    }

    @Test
    public void showRecipeIngredient() throws Exception {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(2L);
        ingredientCommand.setDescription("manos");
        ingredientCommand.setRecipeId(1l);
        when(ingrentientService.findByRecipeIdAndIngrentientId(anyLong(),anyLong())).thenReturn(ingredientCommand);
        mockMvc.perform(get("/recipe/1/ingredient/2/show"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("recipe/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));
    }


    @Test
    public void showIngredient() throws Exception {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(2L);
        ingredientCommand.setDescription("manos");
        ingredientCommand.setRecipeId(1l);
        when(ingrentientService.findByRecipeIdAndIngrentientId(anyLong(),anyLong())).thenReturn(ingredientCommand);

        Set<UnitOfMeasureCommand> set = new HashSet<>();
        UnitOfMeasureCommand uom = new UnitOfMeasureCommand();
        uom.setDescription("malaka");
        uom.setId(1l);
        set.add(uom);
        when(uomService.findAll()).thenReturn(set);


        mockMvc.perform(get("/recipe/1/ingredient/2/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientform"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));

    }


    @Test
    public void updateIngredient() throws Exception {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(3L);
        command.setRecipeId(2L);

        //when
        when(ingrentientService.saveIngredient(any())).thenReturn(command);

        //then
        mockMvc.perform(post("/recipe/2/ingredient")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/ingredient/3/show"));

    }

}