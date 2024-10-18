package Salvo_assenato.Recipe.controllers;

import Salvo_assenato.Recipe.entities.Recipe;
import Salvo_assenato.Recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Recipe")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    //1)CookingMethods of recipe
    @RequestMapping("/CookingMethod-oven")
    public List<Recipe>getRecipeByCookingMethodOven(){
        return recipeService.getOvenCookingMethod();
    }
    @RequestMapping("/CookingMethod-grill")
    public List<Recipe>getRecipeByCookingMethodGrill(){
        return recipeService.getGrillCookingMethod();
    }
    @RequestMapping("/CookingMethod-oven")
    public List<Recipe>getRecipeByCookingMethodBoiling(){
        return recipeService.getBoilingCookingMethod();
    }
    @RequestMapping("/CookingMethod-oven")
    public List<Recipe>getRecipeByCookingMethodFrying(){
        return recipeService.getFryingCookingMethod();
    }
    @RequestMapping("/CookingMethod-oven")
    public List<Recipe>getRecipeByCookingMethodEmbers(){
        return recipeService.getEmbersCookingMethod();
    }
}
