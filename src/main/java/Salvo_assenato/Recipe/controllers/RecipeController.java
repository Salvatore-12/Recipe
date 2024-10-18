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
    @RequestMapping("/CookingMethod-Oven")
    public List<Recipe> getRecipeByCookingMethodOven(){
        return recipeService.getOvenCookingMethod();
    }
    @RequestMapping("/CookingMethod-Grill")
    public List<Recipe> getRecipeByCookingMethodGrill(){
        return recipeService.getGrillCookingMethod();
    }
    @RequestMapping("/CookingMethod-Boiling")
    public List<Recipe> getRecipeByCookingMethodBoiling(){
        return recipeService.getBoilingCookingMethod();
    }
    @RequestMapping("/CookingMethod-Frying")
    public List<Recipe> getRecipeByCookingMethodFrying(){
        return recipeService.getFryingCookingMethod();
    }
    @RequestMapping("/CookingMethod-Embers")
    public List<Recipe> getRecipeByCookingMethodEmbers(){
        return recipeService.getEmbersCookingMethod();
    }

    //2)Dish recipe category
    @RequestMapping("/DishCategory-Appetizer")
    public List<Recipe> getRecipeByDishCategoryAppetizer(){
        return recipeService.getAppetizerDishCategory();
    }
    @RequestMapping("/DishCategory-Main_Course")
    public List<Recipe> getRecipeByDishCategoryMain_Course(){
        return recipeService.getMain_CourseDishCategory();
    }
    @RequestMapping("/DishCategory-Dessert")
    public List<Recipe> getRecipeByDishCategoryDessert(){
        return recipeService.getDessertDishCategory();
    }
    @RequestMapping("/DishCategory-Side_Dish")
    public List<Recipe> getRecipeByDishCategorySide_Dish(){
        return recipeService.getSide_DishDishCategory();
    }
    @RequestMapping("/DishCategory-Salad")
    public List<Recipe> getRecipeByDishCategorySalad(){
        return recipeService.getSaladDishCategory();
    }
    @RequestMapping("/DishCategory-Soup")
    public List<Recipe> getRecipeByDishCategorySoup(){
        return recipeService.getSoupDishCategory();
    }
    @RequestMapping("/DishCategory-Snack")
    public List<Recipe> getRecipeByDishCategorySnack(){
        return recipeService.getSnackDishCategory();
    }
    @RequestMapping("/DishCategory-Beverage")
    public List<Recipe> getRecipeByDishCategoryBeverage(){
        return recipeService.getBeverageDishCategory();
    }

    //3)DishTemperature of recipe
    @RequestMapping("/DishTemperature-Cold")
    public List<Recipe> getRecipeDishTemperatureCold(){
        return recipeService.getColdDishTemperature();
    }
    @RequestMapping("/DishTemperature-Hot")
    public List<Recipe> getRecipeDishTemperatureHot(){
        return recipeService.getHotDishTemperature();
    }
}
