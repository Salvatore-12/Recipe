package Salvo_assenato.Recipe.service;

import Salvo_assenato.Recipe.Enum.*;
import Salvo_assenato.Recipe.entities.Ingredient;
import Salvo_assenato.Recipe.entities.Recipe;
import Salvo_assenato.Recipe.exceptions.NotFoundException;
import Salvo_assenato.Recipe.payloads.IngredientDTO;
import Salvo_assenato.Recipe.payloads.RecipeDTO;
import Salvo_assenato.Recipe.repositories.RecipeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;


@Service
public class RecipeService {
    @Autowired
    private RecipeDAO recipeDAO;


    public Recipe getRecipeById(UUID id) {
        return recipeDAO.findById(id).orElse(null); // Assicurati che questo sia corretto
    }
    private IngredientDTO convertToIngredientDTO(Ingredient ingredient) {
        return new IngredientDTO(
                ingredient.getName(),
                ingredient.getQuantity(),
                ingredient.getUnit()
        );
    }

    public List<Recipe> getRecipeByName(String name){
        return recipeDAO.findByName(name);
    }


    public Ingredient convertToIngredient(IngredientDTO ingredientDTO) {
        if (ingredientDTO == null) {
            throw new IllegalArgumentException("IngredientDTO cannot be null");
        }

        System.out.println("Converting IngredientDTO: " + ingredientDTO);

        // Verifica i valori di ingredientDTO
        if (ingredientDTO.name() == null || ingredientDTO.unit() == null) {
            throw new IllegalArgumentException("IngredientDTO fields cannot be null");
        }

        return new Ingredient(
                null,
                ingredientDTO.name(),
                ingredientDTO.quantity(),
                ingredientDTO.unit()
        );
    }



    public void delete(UUID id){
        recipeDAO.deleteById(id);

    }
    public Recipe saveRecipe(Recipe recipe) {
        return recipeDAO.save(recipe);
    }

    //custom recipe queries

    //1)Query for the CookingMethod
    public List<Recipe>getOvenCookingMethod(){
        return recipeDAO.findByCookingMethod(CookingMethod.OVEN);
    }
    public List<Recipe>getGrillCookingMethod(){
        return recipeDAO.findByCookingMethod(CookingMethod.GRILL);
    }
    public List<Recipe>getBoilingCookingMethod(){
        return recipeDAO.findByCookingMethod(CookingMethod.BOILING);
    }
    public List<Recipe>getFryingCookingMethod(){
        return recipeDAO.findByCookingMethod(CookingMethod.FRYING);
    }
    public List<Recipe>getEmbersCookingMethod(){
        return recipeDAO.findByCookingMethod(CookingMethod.EMBERS);
    }

    //2)Query for the DishTemperature
    public List<Recipe>getColdDishTemperature(){
        return recipeDAO.findByDishTemperature(DishTemperature.COLD);
    }
    public List<Recipe>getHotDishTemperature(){
        return recipeDAO.findByDishTemperature(DishTemperature.HOT);
    }

    //2)Query for the DishCategory
    public List<Recipe>getAppetizerDishCategory(){
        return recipeDAO.findByDishCategory(DishCategory.APPETIZER);
    }
    public List<Recipe>getFirstCourseDishCategory(){
        return recipeDAO.findByDishCategory(DishCategory.FIRST_COURSE);
    }
    public List<Recipe>getSecondCourseDishCategory(){
        return recipeDAO.findByDishCategory(DishCategory.SECOND_COURSE);
    }
    public List<Recipe>getDessertDishCategory(){
        return recipeDAO.findByDishCategory(DishCategory.DESSERT);
    }
    public List<Recipe>getSide_DishDishCategory(){
        return recipeDAO.findByDishCategory(DishCategory.SIDE_DISH);
    }
    public List<Recipe>getSaladDishCategory(){
        return recipeDAO.findByDishCategory(DishCategory.SALAD);
    }
    public List<Recipe>getSoupDishCategory(){
        return recipeDAO.findByDishCategory(DishCategory.SOUP);
    }
    public List<Recipe>getSnackDishCategory(){
        return recipeDAO.findByDishCategory(DishCategory.SNACK);
    }
    public List<Recipe>getBeverageDishCategory(){
        return recipeDAO.findByDishCategory(DishCategory.BEVERAGE);
    }

    //3)Query for the Season Recipe
    public List<Recipe>getSummerSeasonRecipe(){
        return recipeDAO.findBySeason(Season.SUMMER);
    }

    public List<Recipe>getWinterSeasonRecipe(){
        return recipeDAO.findBySeason(Season.WINTER);
    }
    public List<Recipe>getAllSeasonRecipe(){
        return recipeDAO.findBySeason(Season.ALL);
    }

    //4)Query for the Difficulty Recipe
    public List<Recipe>getEasyRecipe(){
        return recipeDAO.findByDifficulty(Difficulty.EASY);
    }
    public List<Recipe>getMediumRecipe(){
        return recipeDAO.findByDifficulty(Difficulty.MEDIUM);
    }
    public List<Recipe>getHardRecipe(){
        return recipeDAO.findByDifficulty(Difficulty.HARD);
    }

    //5)query for the search
    public List<Recipe> searchRecipes(String name, CookingMethod cookingMethod, DishCategory dishCategory) {
        if (StringUtils.hasText(name) && cookingMethod != null && dishCategory != null) {
            return recipeDAO.findByNameContainingIgnoreCaseAndCookingMethodAndDishCategory(name, cookingMethod, dishCategory);
        }
        if (StringUtils.hasText(name) && cookingMethod != null) {
            return recipeDAO.findByNameContainingIgnoreCaseAndCookingMethod(name, cookingMethod);
        }
        if (StringUtils.hasText(name) && dishCategory != null) {
            return recipeDAO.findByNameContainingIgnoreCaseAndDishCategory(name, dishCategory);
        }
        if (cookingMethod != null && dishCategory != null) {
            return recipeDAO.findByCookingMethodAndDishCategory(cookingMethod, dishCategory);
        }
        if (StringUtils.hasText(name)) {
            return recipeDAO.findByNameContainingIgnoreCase(name); // Ricerca con nome parziale
        }
        if (cookingMethod != null) {
            return recipeDAO.findByCookingMethod(cookingMethod);
        }
        if (dishCategory != null) {
            return recipeDAO.findByDishCategory(dishCategory);
        }
        return recipeDAO.findAll();
    }

}
