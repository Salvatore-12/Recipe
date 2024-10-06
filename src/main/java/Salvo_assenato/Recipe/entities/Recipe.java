package Salvo_assenato.Recipe.entities;

import Salvo_assenato.Recipe.Enum.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
@Id
    private UUID idRecipe;
    private String name;
    private String imageUrl;
    private String description;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ingredient> ingredients;
    private String steps;
    private int preparationTime; // tempo di preparazione
    private int cookingTime;     // tempo di cottura
    private int servings;        // porzioni
    @Enumerated(EnumType.STRING)
    private CookingMethod cookingMethod;
    @Enumerated(EnumType.STRING)
    private DishTemperature dishTemperature;
    @Enumerated(EnumType.STRING)
    private DishCategory dishCategory;
    @Enumerated(EnumType.STRING)
    private Season season;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @Override
    public String toString() {
        return "Recipe{" +
                "idRecipe=" + idRecipe +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                ", ingredients=" + ingredients +
                ", steps='" + steps + '\'' +
                ", preparationTime=" + preparationTime +
                ", cookingTime=" + cookingTime +
                ", servings=" + servings +
                ", cookingMethod=" + cookingMethod +
                ", dishTemperature=" + dishTemperature +
                ", dishCategory=" + dishCategory +
                ", season=" + season +
                ", difficulty=" + difficulty +
                '}';
    }
}
