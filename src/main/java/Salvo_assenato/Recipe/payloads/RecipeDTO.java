package Salvo_assenato.Recipe.payloads;

import Salvo_assenato.Recipe.Enum.*;

import java.util.List;
import java.util.SplittableRandom;
import java.util.UUID;

public record RecipeDTO(
        UUID id,
        String name,
        String imageUrl,
        String descripition,
        List<IngredientDTO> ingredients,
        String steps,
        int preparationTime,
        int cookingTime,
        int servings,
        CookingMethod cookingMethod,
        DishTemperature dishTemperature,
        DishCategory dishCategory,
        Season season,
        Difficulty difficulty


) {
}
