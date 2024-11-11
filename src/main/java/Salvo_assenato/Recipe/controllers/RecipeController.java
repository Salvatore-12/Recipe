package Salvo_assenato.Recipe.controllers;

import Salvo_assenato.Recipe.Enum.*;
import Salvo_assenato.Recipe.entities.Ingredient;
import Salvo_assenato.Recipe.entities.Recipe;
import Salvo_assenato.Recipe.exceptions.NotFoundException;
import Salvo_assenato.Recipe.payloads.RecipeDTO;
import Salvo_assenato.Recipe.service.CloudinaryService;
import Salvo_assenato.Recipe.service.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Recipe")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private CloudinaryService cloudinaryService;
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable("id") UUID id) {
        try {
            Recipe recipe = recipeService.getRecipeById(id);
            return ResponseEntity.ok(recipe);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/createRecipeWithImage")
    public ResponseEntity<?> createRecipeWithImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("recipe") String recipeJson) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Deserializzazione del JSON in un oggetto RecipeDTO
            RecipeDTO recipeDTO = objectMapper.readValue(recipeJson, RecipeDTO.class);

            // Crea un oggetto Recipe a partire dal DTO
            Recipe recipe = new Recipe();
            recipe.setIdRecipe(UUID.randomUUID());
            recipe.setName(recipeDTO.name());
            recipe.setDescription(recipeDTO.description());

            // Controlla se il file è valido e caricalo su Cloudinary
            if (file != null && !file.isEmpty()) {
                Map<String, Object> uploadResult = cloudinaryService.uploadImage(file);
                String imageUrl = uploadResult.get("secure_url").toString(); // Ottieni l'URL dell'immagine
                recipe.setImageUrl(imageUrl); // Salva l'URL dell'immagine nella ricetta
            } else {
                recipe.setImageUrl(null); // Imposta null se non c'è immagine
            }

            // Controlla e converti gli ingredienti
            if (recipeDTO.ingredients() != null) {
                List<Ingredient> ingredients = recipeDTO.ingredients().stream()
                        .map(recipeService::convertToIngredient)
                        .collect(Collectors.toList());
                ingredients.forEach(ingredient -> ingredient.setRecipe(recipe));
                recipe.setIngredients(ingredients);
            } else {
                recipe.setIngredients(new ArrayList<>());
            }

            // Imposta i passaggi
            recipe.setSteps(recipeDTO.steps() != null ? recipeDTO.steps() : new ArrayList<>());

            // Imposta altri campi
            recipe.setPreparationTime(recipeDTO.preparationTime());
            recipe.setCookingTime(recipeDTO.cookingTime());
            recipe.setServings(recipeDTO.servings());
            recipe.setCookingMethod(recipeDTO.cookingMethod());
            recipe.setDishTemperature(recipeDTO.dishTemperature());
            recipe.setDishCategory(recipeDTO.dishCategory());
            recipe.setSeason(recipeDTO.season());
            recipe.setDifficulty(recipeDTO.difficulty());

            // Salva la ricetta nel database
            recipeService.saveRecipe(recipe);

            return ResponseEntity.ok("Ricetta creata con successo");
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body("Errore nel parsing del JSON: " + e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Errore nel caricamento dell'immagine: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Errore nel salvataggio della ricetta: " + e.getMessage());
        }
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
    @RequestMapping("/DishCategory-First_Course")
    public List<Recipe> getRecipeByDishCategoryFirstCourse(){
        return recipeService.getFirstCourseDishCategory();
    }
    @RequestMapping("/DishCategory-Second_Course")
    public List<Recipe> getRecipeByDishCategorySecondCourse(){
        return recipeService.getSecondCourseDishCategory();
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

