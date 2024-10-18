package Salvo_assenato.Recipe.controllers;

import Salvo_assenato.Recipe.Enum.*;
import Salvo_assenato.Recipe.entities.Ingredient;
import Salvo_assenato.Recipe.entities.Recipe;
import Salvo_assenato.Recipe.service.CloudinaryService;
import Salvo_assenato.Recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/Recipe")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping("/uploadImage")
    public Map<String, Object> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        return cloudinaryService.uploadImage(file);
    }
    //metodo per creare una ricetta con un'immagine
    @PostMapping("/createRecipeWithImage")
    public Recipe createRecipeWithImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("ingredients") List<Ingredient> ingredients,
            @RequestParam("steps") List<String> steps,
            @RequestParam("preparationTime") int preparationTime,
            @RequestParam("cookingTime") int cookingTime,
            @RequestParam("servings") int servings,
            @RequestParam("cookingMethod") CookingMethod cookingMethod,
            @RequestParam("dishTemperature") DishTemperature dishTemperature,
            @RequestParam("dishCategory") DishCategory dishCategory,
            @RequestParam("season") Season season,
            @RequestParam("difficulty") Difficulty difficulty
    ) throws IOException {
        // Caricamento immagine su Cloudinary
        Map<String, Object> uploadResult = cloudinaryService.uploadImage(file);

        // Creazione della ricetta
        Recipe recipe = new Recipe();
        recipe.setIdRecipe(UUID.randomUUID()); // Generazione di un UUID unico
        recipe.setName(name);
        recipe.setDescription(description);

        // Istruzione degli ingredienti,vari stepse i vari enum
        recipe.setIngredients(ingredients);
        recipe.setSteps(steps);
        recipe.setPreparationTime(preparationTime);
        recipe.setCookingTime(cookingTime);
        recipe.setServings(servings);
        recipe.setCookingMethod(cookingMethod);
        recipe.setDishTemperature(dishTemperature);
        recipe.setDishCategory(dishCategory);
        recipe.setSeason(season);
        recipe.setDifficulty(difficulty);

        // Salvataggio dell'URL immagine dalla risposta di Cloudinary
        if (uploadResult != null && uploadResult.get("url") != null) {
            recipe.setImageUrl(uploadResult.get("url").toString());
        } else {
            throw new RuntimeException("Errore nel caricamento dell'immagine");
        }

        // Salva la ricetta nel database
        return recipeService.saveRecipe(recipe);
    }
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

    //4)Season of recipe
    @RequestMapping("/Season-Summer")
    public List<Recipe> getRecipeSeasonOfSummer(){
        return recipeService.getSummerSeasonRecipe();
    }
    @RequestMapping("/Season-Winter")
    public List<Recipe> getRecipeSeasonOfWinter(){
        return recipeService.getWinterSeasonRecipe();
    }
    @RequestMapping("/Season-AllSeasons")
    public List<Recipe> getRecipeOfAllSeason(){
        return recipeService.getAllSeasonRecipe();
    }

    //4)Difficulty of recipe
    @RequestMapping("/Difficulty-Easy")
    public List<Recipe> getRecipeDifficultyEasy(){
        return recipeService.getEasyRecipe();
    }
    @RequestMapping("/Difficulty-Medium")
    public List<Recipe> getRecipeDifficultyMedium(){
        return recipeService.getMediumRecipe();
    }
    @RequestMapping("/Difficulty-Hard")
    public List<Recipe> getRecipeDifficultyHard(){
        return recipeService.getHardRecipe();
    }
}
