package com.manos.spring5recipeapp.services;

import com.manos.spring5recipeapp.Repositories.RecipeRepository;
import com.manos.spring5recipeapp.commands.RecipeCommand;
import com.manos.spring5recipeapp.converters.RecipeCommandToRecipe;
import com.manos.spring5recipeapp.converters.RecipeToRecipeCommand;
import com.manos.spring5recipeapp.exceptions.NotFoundException;
import com.manos.spring5recipeapp.models.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {


    public static final String DESC = "desc";
    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl();
        recipeService.recipeRepository=recipeRepository;
        recipeService.recipeToRecipeCommand=recipeToRecipeCommand;
        recipeService.recipeCommandToRecipe=recipeCommandToRecipe;

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
    public void findById() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        Recipe recipeServiceById = recipeService.findById(1L);
        assertNotNull(recipeServiceById);
    }

    @Test(expected = NotFoundException.class)
    public void findByIdNotFound() throws Exception {
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.empty());
        recipeService.findById(1L);
    }

    @Test
    public void save() throws Exception {

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);
        recipeCommand.setDescription("desc");
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setDescription(DESC);
        when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe);
        when(recipeCommandToRecipe.convert(any(RecipeCommand.class))).thenReturn(recipe);
        when(recipeToRecipeCommand.convert(any(Recipe.class))).thenReturn(recipeCommand);
        RecipeCommand saved = recipeService.save(recipeCommand);
        assertNotNull(saved);
        assertEquals(DESC,saved.getDescription());
    }

    @Test
    public void findCommandById() throws Exception {

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setDescription(DESC);
        Optional<Recipe> recipOptional = Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(recipOptional);
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);
        recipeCommand.setDescription(DESC);
        when(recipeToRecipeCommand.convert(any(Recipe.class))).thenReturn(recipeCommand);
        RecipeCommand command = recipeService.findCommandById(1L);
        assertNotNull(command);
        assertEquals(DESC,command.getDescription());
    }

    @Test
    public void deleteById() throws Exception {

        //given
        Long idToDelete = Long.valueOf(2L);

        //when
        recipeService.deleteById(idToDelete);


        //then
        verify(recipeRepository, times(1)).deleteById(anyLong());

    }
}