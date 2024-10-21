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

    private void createCustomRecipe(Scanner scanner) {
        try {
            // Creazione di una nuova ricetta
            Recipe customRecipe = new Recipe();
            customRecipe.setIdRecipe(UUID.randomUUID()); // Imposta un nuovo ID per la ricetta

            // Chiedi all'utente di inserire i dettagli della ricetta
            System.out.print("Nome della ricetta: ");
            customRecipe.setName(scanner.nextLine());

            // Chiedi il percorso dell'immagine
            System.out.print("Inserisci il percorso dell'immagine (o lascia vuoto per nessuna immagine): ");
            String imagePath = scanner.nextLine();

            // Se il percorso non è vuoto, carica l'immagine su Cloudinary
            if (!imagePath.isEmpty()) {
                File file = new File(imagePath);
                if (file.exists() && !file.isDirectory()) { // Controlla se il file esiste
                    try (FileInputStream fileInputStream = new FileInputStream(file)) {
                        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "image/jpeg", fileInputStream);
                        Map<String, Object> uploadResult = cloudinaryService.uploadImage(multipartFile);
                        String imageUrl = (String) uploadResult.get("url");
                        customRecipe.setImageUrl(imageUrl); // Salva l'URL dell'immagine nella ricetta
                    }
                } else {
                    System.out.println("Il file non esiste. Nessuna immagine caricata.");
                }
            }

            // Ora chiedi la descrizione della ricetta
            System.out.print("Descrizione della ricetta: ");
            customRecipe.setDescription(scanner.nextLine());

            System.out.print("Tempo di preparazione (in minuti): ");
            customRecipe.setPreparationTime(scanner.nextInt());

            System.out.print("Tempo di cottura (in minuti): ");
            customRecipe.setCookingTime(scanner.nextInt());

            System.out.print("Porzioni: ");
            customRecipe.setServings(scanner.nextInt());
            scanner.nextLine(); // Consuma il newline rimasto

            // Aggiungi gli ingredienti
            List<Ingredient> ingredients = new ArrayList<>();
            String addMore;
            do {
                System.out.print("Nome dell'ingrediente: ");
                String ingredientName = scanner.nextLine();

                System.out.print("Quantità dell'ingrediente: ");
                double ingredientQuantity = scanner.nextDouble();

                System.out.print("Unità dell'ingrediente: ");
                String ingredientUnit = scanner.next();
                scanner.nextLine(); // Consuma il newline rimasto

                // Crea un nuovo ingrediente e impostalo nella ricetta
                Ingredient ingredient = new Ingredient(ingredientName, ingredientQuantity, ingredientUnit);
                ingredient.setRecipe(customRecipe); // Imposta la ricetta all'ingrediente
                ingredients.add(ingredient);

                System.out.print("Vuoi aggiungere un altro ingrediente? (s/n): ");
                addMore = scanner.nextLine();
            } while (addMore.equalsIgnoreCase("s"));

            customRecipe.setIngredients(ingredients); // Imposta gli ingredienti nella ricetta

            // Salva la ricetta nel database
            recipeService.saveRecipe(customRecipe);
            System.out.println("Ricetta \"" + customRecipe.getName() + "\" salvata con successo!");

        } catch (Exception e) {
            System.out.println("Errore durante la creazione della ricetta: " + e.getMessage());
        }
    }
}
