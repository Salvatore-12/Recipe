package Salvo_assenato.Recipe;

import Salvo_assenato.Recipe.Enum.*;
import Salvo_assenato.Recipe.entities.Ingredient;
import Salvo_assenato.Recipe.entities.Recipe;
import Salvo_assenato.Recipe.factory.RecipeFactory;
import Salvo_assenato.Recipe.service.CloudinaryService;
import Salvo_assenato.Recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

@Component
@Order(1)
public class RecipeRunner implements CommandLineRunner {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private RecipeFactory recipeFactory;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean errors = false;

        do {
            System.out.println("Vuoi Procedere Con la Creazione della ricetta? (y/n)");
            String choice = scanner.nextLine();
            switch (choice.toLowerCase()) {
                case "y" -> {
                      //createPizzaMargherita(recipeFactory);
                      //createPizzaQuattroFormaggi(recipeFactory);
                      //createPastaAlPomodoro(recipeFactory);

                    errors = false;
                    System.out.println("ricetta create con successo!");
                }

                case "n" -> errors = false;
                default -> {
                    System.out.println("Input non valido. Riprova.");
                    errors = true;
                }

            }
        } while (errors);

        scanner.close();
    }
 // SEZIONE CREAZIONE PIZZA
    //Metodo per la creazione della ricetta PizzaMargherita
    public void createPizzaMargherita(RecipeFactory recipeFactory) {
        // Lista degli ingredienti per la Pizza Margherita
        List<Ingredient> ingredients = List.of(
                new Ingredient("Farina", 500, "g"),
                new Ingredient("Acqua", 300, "ml"),
                new Ingredient("Lievito di birra", 15, "g"),
                new Ingredient("Sale", 10, "g"),
                new Ingredient("Olio d'oliva", 30, "ml"),
                new Ingredient("Passata di pomodoro", 200, "g"),
                new Ingredient("Mozzarella", 200, "g"),
                new Ingredient("Basilico fresco", 5, "foglie"),
                new Ingredient("Olio d'oliva", 20, "ml")
        );

        // Lista dei passaggi per la Pizza Margherita
        List<String> steps = List.of(
                "Preriscalda il forno a 250°C.",
                "In una ciotola, mescola la farina con il lievito.",
                "Aggiungi l'acqua e l'olio e impasta.",
                "Lascia lievitare per circa 1 ora.",
                "Stendi l'impasto su una teglia e aggiungi il pomodoro.",
                "Aggiungi la mozzarella e l'olio.",
                "Inforna per 15 minuti o fino a doratura.",
                "Aggiungi basilico fresco prima di servire."
        );

        // Creo la ricetta utilizzando la RecipeFactory
        Recipe pizzaMargherita = recipeFactory.createRecipe(
                "Pizza Margherita",
                "Classica pizza italiana con pomodoro, mozzarella e basilico fresco.",
                30, 15, 4,
                CookingMethod.OVEN,
                DishCategory.SECOND_COURSE,
                DishTemperature.HOT,
                Season.ALL,
                Difficulty.MEDIUM,
                ingredients,
                steps,
                "C:\\Users\\UTENTE\\Desktop\\vari progetti\\immagine per ricette\\Pizza_Margherita.jpg"
        );

        // Salva la ricetta nel database
        recipeService.saveRecipe(pizzaMargherita);
        System.out.println("Ricetta Pizza Margherita salvata con successo!");
    }

    public void createPizzaQuattroFormaggi(RecipeFactory recipeFactory) {
        // Lista degli ingredienti per la Pizza Quattro Formaggi
        List<Ingredient> ingredients = List.of(
                new Ingredient("Farina", 500, "g"),
                new Ingredient("Acqua", 300, "ml"),
                new Ingredient("Lievito di birra", 15, "g"),
                new Ingredient("Sale", 10, "g"),
                new Ingredient("Olio d'oliva", 30, "ml"),
                new Ingredient("Mozzarella", 100, "g"),
                new Ingredient("Gorgonzola", 50, "g"),
                new Ingredient("Fontina", 50, "g"),
                new Ingredient("Parmigiano Reggiano", 30, "g"),
                new Ingredient("Olio d'oliva", 20, "ml")
        );

        // Lista dei passaggi per la Pizza Quattro Formaggi
        List<String> steps = List.of(
                "Preriscalda il forno a 250°C.",
                "In una ciotola, mescola la farina con il lievito.",
                "Aggiungi l'acqua e l'olio e impasta fino a ottenere un impasto omogeneo.",
                "Lascia lievitare per circa 1 ora.",
                "Stendi l'impasto su una teglia leggermente oliata.",
                "Distribuisci i formaggi (mozzarella, gorgonzola, fontina, parmigiano) uniformemente sull'impasto.",
                "Aggiungi un filo d'olio d'oliva sopra i formaggi.",
                "Inforna per 15 minuti o fino a doratura.",
                "Sforna e servi la pizza ben calda."
        );

        // Creo la ricetta utilizzando la RecipeFactory
        Recipe pizzaQuattroFormaggi = recipeFactory.createRecipe(
                "Pizza Quattro Formaggi",
                "Pizza italiana con una combinazione di quattro formaggi: mozzarella, gorgonzola, fontina e parmigiano.",
                30, 15, 4,
                CookingMethod.OVEN,
                DishCategory.SECOND_COURSE,
                DishTemperature.HOT,
                Season.ALL,
                Difficulty.MEDIUM,
                ingredients,
                steps,
                "C:\\Users\\UTENTE\\Desktop\\vari progetti\\immagine per ricette\\pizza-quattro-formaggi.jpg"
        );

        // Salva la ricetta nel database
        recipeService.saveRecipe(pizzaQuattroFormaggi);
        System.out.println("Ricetta Pizza Quattro Formaggi salvata con successo!");
    }

// SEZIONE CREAZIONE DELLE PASTE
    public void createPastaAlPomodoro(RecipeFactory recipeFactory) {
        // Lista degli ingredienti per la Pasta al Pomodoro
        List<Ingredient> ingredients = List.of(
                new Ingredient("Pasta (spaghetti)", 400, "g"),
                new Ingredient("Passata di pomodoro", 500, "g"),
                new Ingredient("Aglio", 2, "spicchi"),
                new Ingredient("Olio d'oliva", 50, "ml"),
                new Ingredient("Sale", 10, "g"),
                new Ingredient("Basilico fresco", 10, "foglie"),
                new Ingredient("Parmigiano grattugiato", 50, "g")
        );

        // Lista dei passaggi per la Pasta al Pomodoro
        List<String> steps = List.of(
                "Metti a bollire l'acqua in una pentola grande e aggiungi il sale.",
                "Quando l'acqua bolle, aggiungi la pasta e cuocila al dente.",
                "Nel frattempo, scalda l'olio in una padella e aggiungi l'aglio tritato.",
                "Fai rosolare l'aglio fino a doratura e poi aggiungi la passata di pomodoro.",
                "Lascia cuocere la salsa per 10 minuti.",
                "Scola la pasta e aggiungila alla salsa, mescolando bene.",
                "Servi con basilico fresco e parmigiano grattugiato."
        );

        // Crea la ricetta utilizzando la RecipeFactory
        Recipe pastaAlPomodoro = recipeFactory.createRecipe(
                "Pasta al Pomodoro",
                "Semplice e deliziosa pasta con salsa di pomodoro fresco.",
                10, 15, 4,
                CookingMethod.BOILING,
                DishCategory.FIRST_COURSE,
                DishTemperature.HOT,
                Season.ALL,
                Difficulty.EASY,
                ingredients,
                steps,
                "C:\\Users\\UTENTE\\Desktop\\vari progetti\\immagine per ricette\\pasta al pomodoro.jpg"
        );

        // Salva la ricetta nel database
        recipeService.saveRecipe(pastaAlPomodoro);
        System.out.println("Ricetta Pasta al Pomodoro salvata con successo!");
    }

}
