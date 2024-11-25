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
                        // createVellutataDiZucca(recipeFactory);
                      //createPastaAlPomodoro(recipeFactory);
                      //createParmigianaMelanzane(recipeFactory);
                      //createLasagnaClassica(recipeFactory);
                      createMojitoRecipe(recipeFactory);
                      createSpritzRecipe(recipeFactory);
                      //createCrostiniCaprinoFichi(recipeFactory);
                      //createMelanzaneParmigianaGrigliate(recipeFactory);


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

    //CREAZIONE ALTRE RICETTE AL FORNO
    public void createParmigianaMelanzane(RecipeFactory recipeFactory) {
        // Lista degli ingredienti per la Parmigiana di Melanzane
        List<Ingredient> ingredients = List.of(
                new Ingredient("Melanzane", 800, "g"),
                new Ingredient("Passata di pomodoro", 400, "g"),
                new Ingredient("Mozzarella", 250, "g"),
                new Ingredient("Parmigiano Reggiano", 100, "g"),
                new Ingredient("Farina", 50, "g"),
                new Ingredient("Olio di semi", 200, "ml"),
                new Ingredient("Basilico fresco", 10, "foglie"),
                new Ingredient("Sale", 10, "g"),
                new Ingredient("Olio d'oliva", 20, "ml")
        );

        // Lista dei passaggi per la Parmigiana di Melanzane
        List<String> steps = List.of(
                "Lava e taglia le melanzane a fette sottili.",
                "Infarina le fette di melanzane e friggile in olio di semi caldo.",
                "Preriscalda il forno a 180°C.",
                "In una teglia, stendi uno strato di passata di pomodoro.",
                "Adagia uno strato di melanzane fritte, aggiungi mozzarella, parmigiano e basilico.",
                "Ripeti gli strati fino a esaurire gli ingredienti.",
                "Termina con uno strato di pomodoro e parmigiano.",
                "Inforna per 25-30 minuti fino a doratura.",
                "Servi caldo o tiepido."
        );

        // Creo la ricetta utilizzando la RecipeFactory
        Recipe parmigianaMelanzane = recipeFactory.createRecipe(
                "Parmigiana di Melanzane",
                "Un classico della cucina italiana con melanzane, pomodoro e formaggio.",
                45, 30, 4,
                CookingMethod.OVEN,
                DishCategory.SECOND_COURSE,
                DishTemperature.HOT,
                Season.ALL,
                Difficulty.MEDIUM,
                ingredients,
                steps,
                "C:\\Users\\UTENTE\\Desktop\\vari progetti\\immagine per ricette\\Parmigiana di Melanzane.jpg"
        );

        // Salva la ricetta nel database
        recipeService.saveRecipe(parmigianaMelanzane);
        System.out.println("Ricetta Parmigiana di Melanzane salvata con successo!");
    }

    public void createLasagnaClassica(RecipeFactory recipeFactory) {

        List<Ingredient> ingredients = List.of(
                new Ingredient("Sfoglie di lasagna", 250, "g"),
                new Ingredient("Ragù di carne", 500, "g"),
                new Ingredient("Besciamella", 400, "ml"),
                new Ingredient("Parmigiano Reggiano", 150, "g"),
                new Ingredient("Mozzarella", 200, "g"),
                new Ingredient("Burro", 20, "g"),
                new Ingredient("Sale", 5, "g")
        );

        List<String> steps = List.of(
                "Preriscalda il forno a 180°C.",
                "Imburra una teglia da forno.",
                "Stendi uno strato di besciamella sul fondo della teglia.",
                "Aggiungi uno strato di sfoglie di lasagna, poi il ragù, la besciamella, la mozzarella e il parmigiano.",
                "Ripeti gli strati fino a esaurire gli ingredienti.",
                "Termina con uno strato di besciamella e abbondante parmigiano.",
                "Inforna per 30-40 minuti fino a che la superficie non risulta dorata.",
                "Lascia riposare per 10 minuti prima di servire."
        );

        Recipe lasagnaClassica = recipeFactory.createRecipe(
                "Lasagna Classica",
                "La tradizionale lasagna italiana con ragù, besciamella e parmigiano.",
                60, 40, 6,
                CookingMethod.OVEN,
                DishCategory.FIRST_COURSE,
                DishTemperature.HOT,
                Season.ALL,
                Difficulty.HARD,
                ingredients,
                steps,
                "C:\\Users\\UTENTE\\Desktop\\vari progetti\\immagine per ricette\\Lasagne al forno.jpg"
        );

        recipeService.saveRecipe(lasagnaClassica);
        System.out.println("Ricetta Lasagna Classica salvata con successo!");
    }
//
// SEZIONE CREAZIONE DELLE Ricette Bollite
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

   public void createVellutataDiZucca(RecipeFactory recipeFactory) {
        List<Ingredient> ingredients = List.of(
                new Ingredient("Zucca", 600, "g"),
                new Ingredient("Cipolla", 1, "pezzo"),
                new Ingredient("Aglio", 1, "spicchio"),
                new Ingredient("Brodo vegetale", 1, "l"),
                new Ingredient("Olio d'oliva", 30, "ml"),
                new Ingredient("Sale", 5, "g"),
                new Ingredient("Pepe nero", 2, "g"),
                new Ingredient("Panna fresca", 50, "ml"),
                new Ingredient("Parmigiano grattugiato", 20, "g")
        );

        List<String> steps = List.of(
                "Taglia la zucca a cubetti e la cipolla a fettine sottili.",
                "In una pentola, scalda l'olio d'oliva e fai soffriggere la cipolla e l'aglio tritato fino a renderli morbidi.",
                "Aggiungi la zucca e cuoci per 5 minuti, mescolando frequentemente.",
                "Aggiungi il brodo vegetale, porta a bollore, quindi abbassa il fuoco e lascia cuocere per 20-25 minuti, fino a quando la zucca non è morbida.",
                "Usa un frullatore a immersione per frullare il tutto fino a ottenere una consistenza vellutata.",
                "Aggiusta di sale e pepe a piacere, e aggiungi la panna fresca per rendere la vellutata più cremosa.",
                "Servi la vellutata calda, guarnita con parmigiano grattugiato e un filo d'olio d'oliva."
        );

        Recipe vellutataDiZucca = recipeFactory.createRecipe(
                "Vellutata di Zucca",
                "Un antipasto caldo e cremoso, perfetto per l'autunno o l'inverno, con zucca e un tocco di panna.",
                10, 35, 4,
                CookingMethod.BOILING,
                DishCategory.APPETIZER,
                DishTemperature.HOT,
                Season.ALL,
                Difficulty.EASY,
                ingredients,
                steps,
                "C:\\Users\\UTENTE\\Desktop\\vari progetti\\immagine per ricette\\VellutataDiZucca.jpg"
        );

        recipeService.saveRecipe(vellutataDiZucca);
        System.out.println("Ricetta Vellutata di Zucca salvata con successo!");
    }
    //SEZIONE RICETTE ALLA GRIGLIA

    public void createCrostiniCaprinoFichi(RecipeFactory recipeFactory) {
        List<Ingredient> ingredients = List.of(
                new Ingredient("Pane rustico", 8, "fette"),
                new Ingredient("Formaggio caprino fresco", 200, "g"),
                new Ingredient("Fichi freschi", 4, "pezzi"),
                new Ingredient("Miele", 30, "ml"),
                new Ingredient("Rosmarino fresco", 1, "rametto"),
                new Ingredient("Olio d'oliva", 20, "ml")
        );

        List<String> steps = List.of(
                "Taglia i fichi a fettine sottili.",
                "Griglia le fette di pane su entrambi i lati fino a doratura.",
                "Spalma il caprino fresco su ogni fetta di pane.",
                "Aggiungi una fetta di fico su ciascun crostino.",
                "Completa con un filo di miele e qualche ago di rosmarino.",
                "Servi immediatamente come antipasto elegante."
        );

        Recipe crostiniCaprinoFichi = recipeFactory.createRecipe(
                "Crostini Grigliati con Caprino e Fichi",
                "Un antipasto raffinato con pane grigliato, caprino cremoso e fichi dolci.",
                10, 5, 4,
                CookingMethod.GRILL,
                DishCategory.APPETIZER,
                DishTemperature.HOT,
                Season.ALL,
                Difficulty.EASY,
                ingredients,
                steps,
                "C:\\Users\\UTENTE\\Desktop\\vari progetti\\immagine per ricette\\crostino-con-caprinoFico.jpg"
        );

        recipeService.saveRecipe(crostiniCaprinoFichi);
        System.out.println("Ricetta Crostini Grigliati con Caprino e Fichi salvata con successo!");
    }

    public void createMelanzaneParmigianaGrigliate(RecipeFactory recipeFactory) {
        List<Ingredient> ingredients = List.of(
                new Ingredient("Melanzane", 2, "pezzi"),
                new Ingredient("Passata di pomodoro", 200, "ml"),
                new Ingredient("Mozzarella", 150, "g"),
                new Ingredient("Parmigiano Reggiano", 50, "g"),
                new Ingredient("Basilico fresco", 8, "foglie"),
                new Ingredient("Olio d'oliva", 30, "ml"),
                new Ingredient("Sale", 5, "g"),
                new Ingredient("Pepe", 3, "g")
        );

        List<String> steps = List.of(
                "Taglia le melanzane a fette di circa 1 cm di spessore.",
                "Spennella le fette di melanzana con olio e condiscile con sale e pepe.",
                "Griglia le melanzane per 3-4 minuti per lato fino a quando sono morbide e con le righe dorate.",
                "Disponi le melanzane grigliate in una teglia alternandole con strati di passata di pomodoro, mozzarella e Parmigiano.",
                "Termina con uno strato di Parmigiano e cuoci sotto il grill del forno per 5 minuti, finché il formaggio è dorato.",
                "Guarnisci con foglie di basilico fresco prima di servire."
        );

        Recipe melanzaneParmigianaGrigliate = recipeFactory.createRecipe(
                "Melanzane alla Parmigiana Grigliate",
                "Un antipasto leggero e saporito ispirato alla parmigiana di melanzane.",
                15, 10, 4,
                CookingMethod.GRILL,
                DishCategory.APPETIZER,
                DishTemperature.HOT,
                Season.ALL,
                Difficulty.MEDIUM,
                ingredients,
                steps,
                "C:\\Users\\UTENTE\\Desktop\\vari progetti\\immagine per ricette\\Parmigiana-di-melanzane-grigliate-.jpg"
        );

        recipeService.saveRecipe(melanzaneParmigianaGrigliate);
        System.out.println("Ricetta Melanzane alla Parmigiana Grigliate salvata con successo!");
    }

    // SEZIONE CREAZIONE BEVANDE

    public void createMojitoRecipe(RecipeFactory recipeFactory) {

        List<Ingredient> ingredients = List.of(
                new Ingredient("Rum bianco", 50, "ml"),
                new Ingredient("Zucchero di canna", 2, "cucchiaini"),
                new Ingredient("Lime", 1, "unità"),
                new Ingredient("Foglie di menta fresca", 10, "foglie"),
                new Ingredient("Acqua frizzante", 100, "ml"),
                new Ingredient("Ghiaccio tritato", 150, "g")
        );

        List<String> steps = List.of(
                "Taglia il lime a spicchi e mettilo in un bicchiere.",
                "Aggiungi lo zucchero di canna e pesta delicatamente per estrarre il succo dal lime.",
                "Aggiungi le foglie di menta e schiaccia leggermente con il pestello.",
                "Riempi il bicchiere con ghiaccio tritato.",
                "Versa il rum bianco sopra il ghiaccio.",
                "Completa con acqua frizzante e mescola delicatamente.",
                "Guarnisci con una foglia di menta fresca e servi."
        );

        Recipe mojito = recipeFactory.createRecipe(
                "Mojito",
                "Cocktail rinfrescante a base di rum, lime, menta e zucchero di canna.",
                10, // Tempo di preparazione
                0,  // Tempo di cottura (non richiesto per i cocktail)
                1,  // Porzioni
                CookingMethod.NO_COOKING, // Nessuna cottura
                DishCategory.ALCOHOLIC_BEVERAGE,    // Categoria: Bevanda
                DishTemperature.COLD,     // Temperatura: Freddo
                Season.SUMMER,            // Stagione: Estate
                Difficulty.EASY,          // Difficoltà: Facile
                ingredients,
                steps,
                "C:\\Users\\UTENTE\\Desktop\\vari progetti\\immagine per ricette\\Mojito.jpg"
        );

        recipeService.saveRecipe(mojito);
        System.out.println("Ricetta Mojito salvata con successo!");
    }

    public void createSpritzRecipe(RecipeFactory recipeFactory) {
        // Lista degli ingredienti per lo Spritz
        List<Ingredient> ingredients = List.of(
                new Ingredient("Prosecco", 60, "ml"),
                new Ingredient("Aperol", 40, "ml"),
                new Ingredient("Acqua frizzante", 20, "ml"),
                new Ingredient("Ghiaccio", 150, "g"),
                new Ingredient("Fetta d'arancia", 1, "unità")
        );

        // Lista dei passaggi per lo Spritz
        List<String> steps = List.of(
                "Riempi un bicchiere da vino con ghiaccio.",
                "Versa il Prosecco nel bicchiere.",
                "Aggiungi l'Aperol sopra il Prosecco.",
                "Completa con acqua frizzante e mescola delicatamente.",
                "Guarnisci con una fetta d'arancia e servi."
        );

        // Creo la ricetta utilizzando la RecipeFactory
        Recipe spritz = recipeFactory.createRecipe(
                "Spritz",
                "Cocktail italiano leggero e frizzante, perfetto per l'aperitivo.",
                5, 0, 1,
                CookingMethod.NO_COOKING,
                DishCategory.ALCOHOLIC_BEVERAGE,
                DishTemperature.COLD,
                Season.ALL,
                Difficulty.EASY,
                ingredients,
                steps,
                "C:\\Users\\UTENTE\\Desktop\\vari progetti\\immagine per ricette\\Spritz.jpg"
        );

        // Salva la ricetta nel database
        recipeService.saveRecipe(spritz);
        System.out.println("Ricetta Spritz salvata con successo!");
    }

}
