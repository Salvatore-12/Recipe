package Salvo_assenato.Recipe.factory;

import Salvo_assenato.Recipe.Enum.*;
import Salvo_assenato.Recipe.entities.Ingredient;
import Salvo_assenato.Recipe.entities.Recipe;
import Salvo_assenato.Recipe.service.CloudinaryService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@Component
public class RecipeFactory {
    private final CloudinaryService cloudinaryService;

    public RecipeFactory(CloudinaryService cloudinaryService) {
        this.cloudinaryService = cloudinaryService;
    }

    public Recipe createRecipe(String name, String description, int preparationTime, int cookingTime, int servings,
                               CookingMethod method, DishCategory category, DishTemperature temperature, Season season, Difficulty difficulty,
                               List<Ingredient> ingredients, List<String> steps, String imageName) {

        Recipe recipe = new Recipe();
        recipe.setIdRecipe(UUID.randomUUID());
        recipe.setName(name);
        recipe.setDescription(description);
        recipe.setPreparationTime(preparationTime);
        recipe.setCookingTime(cookingTime);
        recipe.setServings(servings);
        recipe.setCookingMethod(method);
        recipe.setDishCategory(category);
        recipe.setDishTemperature(temperature);
        recipe.setSeason(season);
        recipe.setDifficulty(difficulty);

        for (Ingredient ingredient : ingredients) {
            ingredient.setRecipe(recipe);
        }
        recipe.setIngredients(ingredients);

        recipe.setSteps(steps);

        // Caricamento immagine su Cloudinary
        if (imageName != null && !imageName.isEmpty()) {
            try {
                ClassPathResource imageResource = new ClassPathResource("images/" + imageName);
                try (InputStream inputStream = imageResource.getInputStream()) {
                    MultipartFile multipartFile = new MockMultipartFile(
                            "file",
                            imageName,
                            "image/jpeg",
                            inputStream
                    );
                    Map<String, Object> uploadResult = cloudinaryService.uploadImage(multipartFile);
                    String imageUrl = (String) uploadResult.get("secure_url");
                    recipe.setImageUrl(imageUrl);
                }
            } catch (IOException e) {
                System.out.println("Errore nel caricamento dell'immagine: " + e.getMessage());
            }
        }

        return recipe;
    }
}
