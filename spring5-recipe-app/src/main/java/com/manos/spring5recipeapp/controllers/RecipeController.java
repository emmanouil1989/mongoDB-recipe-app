package com.manos.spring5recipeapp.controllers;

import com.manos.spring5recipeapp.commands.RecipeCommand;
import com.manos.spring5recipeapp.exceptions.NotFoundException;
import com.manos.spring5recipeapp.services.RecipeService;
import com.sun.tracing.dtrace.ProviderAttributes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Binding;
import javax.validation.Valid;

@Controller
@Slf4j
public class RecipeController {

    private static final String RECIPE_RECIPEFORM = "recipe/recipeform";
    @Autowired
    RecipeService recipeService;

    @GetMapping("recipe/{id}/show")
    public String showById(@PathVariable String id,Model model){
        model.addAttribute("recipe",recipeService.findById(new Long(id)));
        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe",new RecipeCommand());
    return RECIPE_RECIPEFORM;
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand recipeCommand, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.toString()));
            return RECIPE_RECIPEFORM;
        }

        RecipeCommand command = recipeService.save(recipeCommand);
        return "redirect:/recipe/" + command.getId()+ "/show/";

    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id,Model model){
        model.addAttribute("recipe",recipeService.findCommandById(new Long(id)));
        return RECIPE_RECIPEFORM;
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteById(@PathVariable String id){
        recipeService.deleteById(new Long(id));
        return "redirect:/recipes";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){
        log.error(exception.getMessage());
        log.error("Handling not found exception");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");
        modelAndView.addObject("exception",exception);
        return modelAndView;
    }

}
