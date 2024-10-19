package Salvo_assenato.Recipe.entities;

import Salvo_assenato.Recipe.Enum.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    @Column(name = "imageUrl")
    private String imageUrl;
    private String description;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Ingredient> ingredients;
    @ElementCollection
    @CollectionTable(name = "recipe_steps", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "step")
    private List<String> steps = new ArrayList<>();
    private int preparationTime; // tempo di preparazione
    private int cookingTime;     // tempo di cottura
    private int servings;        // porzioni
    @Enumerated(EnumType.STRING)
    @JsonProperty("cookingMethod")
    private CookingMethod cookingMethod;
    @Enumerated(EnumType.STRING)
    @JsonProperty("dishTemperature")
    private DishTemperature dishTemperature;
    @Enumerated(EnumType.STRING)
    @JsonProperty("dishCategory")
    private DishCategory dishCategory;
    @Enumerated(EnumType.STRING)
    @JsonProperty("season")
    private Season season;
    @JsonProperty("difficulty")
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;


    @Override
    public String toString() {
        String result = "Recipe{" +
                "idRecipe=" + idRecipe +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                ", ingredients=" + ingredients +
                ", steps=\n";

        // Aggiunta degli step con i numeri
        for (int i = 0; i < steps.size(); i++) {
            result += (i + 1) + ". " + steps.get(i) + "\n";
        }

        result +=
                ", preparationTime=" + preparationTime +
                        ", cookingTime=" + cookingTime +
                        ", servings=" + servings +
                        ", cookingMethod=" + cookingMethod +
                        ", dishTemperature=" + dishTemperature +
                        ", dishCategory=" + dishCategory +
                        ", season=" + season +
                        ", difficulty=" + difficulty +
                        '}';

        return result;
    }
}
