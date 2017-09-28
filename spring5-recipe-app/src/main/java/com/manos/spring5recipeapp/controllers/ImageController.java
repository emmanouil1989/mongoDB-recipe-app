package com.manos.spring5recipeapp.controllers;

import com.manos.spring5recipeapp.commands.RecipeCommand;
import com.manos.spring5recipeapp.models.Recipe;
import com.manos.spring5recipeapp.services.ImageService;
import com.manos.spring5recipeapp.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {

    @Autowired
    public ImageService imageService;

    @Autowired
    public RecipeService recipeService;

    @GetMapping("/recipe/{id}/image")
    public String getImageForm(@PathVariable String id, Model model){
        RecipeCommand recipe = recipeService.findCommandById(Long.valueOf(id));
        model.addAttribute("recipe",recipe);
        return "recipe/imageuploadform";
    }

    @PostMapping("/recipe/{id}/image")
    public String saveImage(@PathVariable String id, @PathVariable MultipartFile file){
        imageService.saveImageFile(Long.valueOf(id),file);
        return "redirect:/recipe/" + id + "/show";
    }
}
