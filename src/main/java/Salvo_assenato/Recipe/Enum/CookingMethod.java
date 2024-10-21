package Salvo_assenato.Recipe.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CookingMethod {
    OVEN,      // Forno
    GRILL,     // Griglia
    BOILING,   // Bollitura
    FRYING,    // Frittura
    EMBERS; // Brace

    @JsonCreator
    public static CookingMethod fromString(String key) {
        return key == null ? null : CookingMethod.valueOf(key.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
