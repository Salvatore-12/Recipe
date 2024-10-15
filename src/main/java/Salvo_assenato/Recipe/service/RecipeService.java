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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    @Autowired
    private RecipeDAO recipeDAO;

    public Recipe findById(UUID id){
        return recipeDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public RecipeDTO getRecipeById(UUID id){

        Recipe recipe =findById(id);

        return  new RecipeDTO(
                recipe.getIdRecipe(),
                recipe.getName(),
                recipe.getImageUrl(),
                recipe.getDescription(),

                recipe.getIngredients().stream()
                        .map(this::convertToIngredientDTO)
                        .collect(Collectors.toList()),
                recipe.getSteps(),
                recipe.getPreparationTime(),
                recipe.getCookingTime(),
                recipe.getServings(),
                recipe.getCookingMethod(),
                recipe.getDishTemperature(),
                recipe.getDishCategory(),
                recipe.getSeason(),
                recipe.getDifficulty()


        );
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

    public Recipe update(UUID id, RecipeDTO body) {
        // Trova la ricetta esistente per l'ID fornito
        Recipe found = this.findById(id);

        // Aggiorna i campi della ricetta con i dati del RecipeDTO
        found.setName(body.name());
        found.setImageUrl(body.imageUrl());
        found.setDescription(body.description());
        found.setIngredients(body.ingredients().stream()
                .map(this::convertToIngredient)
                .collect(Collectors.toList()));
        found.setSteps(body.steps());
        found.setPreparationTime(body.preparationTime());
        found.setCookingTime(body.cookingTime());
        found.setServings(body.servings());
        found.setCookingMethod(body.cookingMethod());
        found.setDishTemperature(body.dishTemperature());
        found.setDishCategory(body.dishCategory());
        found.setSeason(body.season());
        found.setDifficulty(body.difficulty());

        return recipeDAO.save(found);
    }

    private Ingredient convertToIngredient(IngredientDTO ingredientDTO) {
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
    public List<Recipe>getMain_CourseDishCategory(){
        return recipeDAO.findByDishCategory(DishCategory.MAIN_COURSE);
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
}
