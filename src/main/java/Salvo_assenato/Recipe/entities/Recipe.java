package Salvo_assenato.Recipe.entities;

import Salvo_assenato.Recipe.Enum.CookingMethod;
import Salvo_assenato.Recipe.Enum.DishTemperature;
import Salvo_assenato.Recipe.Enum.Season;

import java.util.List;

public class Recipe {
    private String name;
    private String image;
    private String description;
    private List<Ingredient> ingredients;
    private String steps;
    private CookingMethod cookingMethod;
    private DishTemperature dishTemperature;
    private Season season;




}
