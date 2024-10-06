package Salvo_assenato.Recipe.payloads;

public record IngredientDTO(
        String name,
        double quantity,
        String unit
) {
}
