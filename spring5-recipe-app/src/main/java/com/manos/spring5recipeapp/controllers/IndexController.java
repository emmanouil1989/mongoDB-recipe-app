package com.manos.spring5recipeapp.controllers;

import com.manos.spring5recipeapp.models.Recipe;
import com.manos.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@Slf4j
public class IndexController {

    @Autowired
    public RecipeService recipeService;

    @RequestMapping({"","/","index"})
    public String index(){
        return "index";
    }



    @RequestMapping("/recipes")
    public String getAllRecipes(Model model){

        ArrayList<Recipe> allRecipes = recipeService.getAllRecipes();
        log.info("incoming recipes");
        model.addAttribute("recipes",allRecipes);
        return "index";
    }
}
