package Salvo_assenato.Recipe.service;

import Salvo_assenato.Recipe.Enum.CookingMethod;
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
    public List<Recipe>getEMBERSCookingMethod(){
        return recipeDAO.findByCookingMethod(CookingMethod.EMBERS);
    }


}
