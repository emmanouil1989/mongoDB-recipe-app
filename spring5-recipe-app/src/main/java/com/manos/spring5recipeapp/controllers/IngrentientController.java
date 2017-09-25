package com.manos.spring5recipeapp.controllers;

import com.manos.spring5recipeapp.commands.IngredientCommand;
import com.manos.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.manos.spring5recipeapp.models.UnitOfMeasure;
import com.manos.spring5recipeapp.services.IngrentientService;
import com.manos.spring5recipeapp.services.RecipeService;
import com.manos.spring5recipeapp.services.UnitOfMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class IngrentientController {

    @Autowired
    RecipeService recipeService;

    @Autowired
    IngrentientService ingrentientService;

    @Autowired
    UnitOfMeasureService unitOfMeasureService;

    @GetMapping
    @RequestMapping("/recipe/{id}/ingredients")
    public String listOfIngrentients(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String ingredientId ,Model model){
        IngredientCommand ingredientCommand = ingrentientService.findByRecipeIdAndIngrentientId(Long.valueOf(recipeId), Long.valueOf(ingredientId));
        model.addAttribute("ingredient",ingredientCommand);
        return "recipe/ingredient/show";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String showIngredient(@PathVariable String recipeId, @PathVariable String ingredientId ,Model model){
        IngredientCommand ingredientCommand = ingrentientService.findByRecipeIdAndIngrentientId(Long.valueOf(recipeId), Long.valueOf(ingredientId));

        Set<UnitOfMeasureCommand> all = unitOfMeasureService.findAll();
        model.addAttribute("ingredient",ingredientCommand);
        model.addAttribute("uomList",all);
        return "recipe/ingredient/ingredientform";
    }

    @PostMapping
    @RequestMapping("recipe/{recipeId}/ingredient")
    public String updateIngredient(@ModelAttribute IngredientCommand command){
        IngredientCommand ingredientCommand = ingrentientService.saveIngredient(command);
        return "redirect:/recipe/" + ingredientCommand.getRecipeId()+ "/ingredient/" + ingredientCommand.getId() + "/show";
    }

}
