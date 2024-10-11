package Salvo_assenato.Recipe.repositories;

import Salvo_assenato.Recipe.Enum.*;
import Salvo_assenato.Recipe.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface RepiceDAO extends JpaRepository<Recipe, UUID> {
    @Override
    Optional<Recipe> findById(UUID id);
    List<Recipe>findByNome(String name);
    List<Recipe>findByCookingMethod(CookingMethod cookingMethod);
    List<Recipe>findByDishTemperature(DishTemperature dishTemperature);
    List<Recipe>findByDishCategory(DishCategory dishCategory);
    List<Recipe>findBySeason(Season season);
    List<Recipe>findByDifficulty(Difficulty difficulty);
    public void deleteById(UUID id);
}
