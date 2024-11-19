package Salvo_assenato.Recipe.repositories;

import Salvo_assenato.Recipe.Enum.*;
import Salvo_assenato.Recipe.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface RecipeDAO extends JpaRepository<Recipe, UUID> {
    @Override
    Optional<Recipe> findById(UUID id);
    List<Recipe>findByName(String name);
    List<Recipe>findByCookingMethod(CookingMethod cookingMethod);
    List<Recipe>findByDishTemperature(DishTemperature dishTemperature);
    List<Recipe>findByDishCategory(DishCategory dishCategory);

    // Methods for the search
    List<Recipe> findByNameIgnoreCase(String name);
    List<Recipe> findByNameContainingIgnoreCase(String name); // ricerca case-insensitive con nome parziale
    List<Recipe> findByNameContainingIgnoreCaseAndCookingMethod(String name, CookingMethod cookingMethod);
    List<Recipe> findByNameContainingIgnoreCaseAndDishCategory(String name, DishCategory dishCategory);
    List<Recipe> findByNameContainingIgnoreCaseAndCookingMethodAndDishCategory(String name, CookingMethod cookingMethod, DishCategory dishCategory);
    @Query("SELECT r FROM Recipe r WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Recipe> searchByName(@Param("name") String name);
    List<Recipe> findByNameAndCookingMethod(String name, CookingMethod cookingMethod);
    List<Recipe> findByNameAndDishCategory(String name, DishCategory dishCategory);
    List<Recipe> findByCookingMethodAndDishCategory(CookingMethod cookingMethod, DishCategory dishCategory);
    List<Recipe> findByNameAndCookingMethodAndDishCategory(String name, CookingMethod cookingMethod, DishCategory dishCategory);


    List<Recipe>findBySeason(Season season);
    List<Recipe>findByDifficulty(Difficulty difficulty);
    public void deleteById(UUID id);
}
