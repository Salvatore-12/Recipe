package Salvo_assenato.Recipe;

import Salvo_assenato.Recipe.Enum.*;
import Salvo_assenato.Recipe.entities.Ingredient;
import Salvo_assenato.Recipe.entities.Recipe;
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
    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean errors = false;

        do {
            System.out.println("Vuoi Procedere Con la Creazione delle ricette? (y/n)");
            String choice = scanner.nextLine();
            switch (choice.toLowerCase()) {
                case "y"->{
                    createRecipe();
                    errors = false;
                    System.out.println("ricette create con successo!");
                }

                case "n"-> errors = false;
                default ->{System.out.println("Input non valido. Riprova.");
                    errors = true;}

            }
        } while (errors);

        scanner.close();
    }
    public void createRecipe() {
        try {
            // Crea una nuova ricetta - Pizza Margherita
            Recipe pizzaMargherita = new Recipe();
            pizzaMargherita.setIdRecipe(UUID.randomUUID());
            pizzaMargherita.setName("Pizza Margherita");
            pizzaMargherita.setDescription("Classica pizza italiana con pomodoro, mozzarella e basilico fresco.");
            pizzaMargherita.setPreparationTime(30); // Tempo di preparazione in minuti
            pizzaMargherita.setCookingTime(15);     // Tempo di cottura in minuti
            pizzaMargherita.setServings(4);         // Porzioni
            pizzaMargherita.setCookingMethod(CookingMethod.OVEN);  // Metodo di cottura: Forno
            pizzaMargherita.setDishTemperature(DishTemperature.HOT); // Temperatura del piatto: Caldo
            pizzaMargherita.setDishCategory(DishCategory.MAIN_COURSE); // Categoria: Secondo
            pizzaMargherita.setSeason(Season.ALL); // Stagione: Tutto l'anno
            pizzaMargherita.setDifficulty(Difficulty.MEDIUM); // Difficoltà: Media

            // Aggiungo gli ingredienti per la Pizza Margherita
            List<Ingredient> ingredients = new ArrayList<>();
            ingredients.add(new Ingredient("Farina", 500, "g"));           // Utilizza il costruttore senza ID
            ingredients.add(new Ingredient("Acqua", 300, "ml"));
            ingredients.add(new Ingredient("Lievito di birra", 15, "g"));
            ingredients.add(new Ingredient("Sale", 10, "g"));
            ingredients.add(new Ingredient("Olio d'oliva", 30, "ml"));
            ingredients.add(new Ingredient("Passata di pomodoro", 200, "g"));
            ingredients.add(new Ingredient("Mozzarella", 200, "g"));
            ingredients.add(new Ingredient("Basilico fresco", 5, "foglie"));
            ingredients.add(new Ingredient("Olio d'oliva", 20, "ml"));

            for (Ingredient ingredient : ingredients) {
                ingredient.setRecipe(pizzaMargherita);
            }

            pizzaMargherita.setIngredients(ingredients);
            // Aggiungo i vari  passaggi per la preparazione
            List<String> steps = new ArrayList<>();
            steps.add("Preriscalda il forno a 250°C.");
            steps.add("In una ciotola, mescola la farina con il lievito.");
            steps.add("Aggiungi l'acqua e l'olio d'oliva e impasta fino ad ottenere un impasto liscio.");
            steps.add("Lascia lievitare l'impasto coperto per circa 1 ora.");
            steps.add("Stendi l'impasto su una teglia, creando un bordo.");
            steps.add("Spalma la passata di pomodoro sulla base della pizza.");
            steps.add("Aggiungi la mozzarella a fette e un filo d'olio d'oliva.");
            steps.add("Inforna la pizza per circa 15 minuti o fino a doratura.");
            steps.add("Aggiungi il basilico fresco prima di servire.");

            pizzaMargherita.setSteps(steps);

            // Caricamento dell'immagine su Cloudinary
            File file = new File("C:\\Users\\UTENTE\\Desktop\\immagine per ricette\\Pizza_Margherita.jpg"); // Cambia con il tuo percorso
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "image/jpeg", fileInputStream);
                Map<String, Object> uploadResult = cloudinaryService.uploadImage(multipartFile);
                String imageUrl = (String) uploadResult.get("url");
                pizzaMargherita.setImageUrl(imageUrl); // Salva l'URL come stringa
            }

            // Salvo la ricetta nel database
            recipeService.saveRecipe(pizzaMargherita);

            System.out.println("Ricetta Pizza Margherita salvata con successo!");

        } catch (Exception e) {
            System.out.println("Errore durante la creazione della ricetta: " + e.getMessage());
        }
    }

}
