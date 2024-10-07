package Salvo_assenato.Recipe.payloads;

import java.util.List;

public record ErrorPayloadsList(String messsage,
                                List<String> errorsList
) {
}
