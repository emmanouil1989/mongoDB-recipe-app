package com.manos.jokesapp.controllers;

import com.manos.jokesapp.services.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JokeController {

    @Autowired
    JokeService jokeService;

    @GetMapping({"/",""})
    public String chuckNoris(Model model){
        model.addAttribute("joke",jokeService.getRandomJokes());
        return "chucknorris";
    }

}
