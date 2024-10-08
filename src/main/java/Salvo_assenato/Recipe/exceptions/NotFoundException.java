package Salvo_assenato.Recipe.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException{

    public NotFoundException(UUID id){
        super("Item with id:" + id + "not found");
    }

    public NotFoundException(String message){
        super(message);
    }
}
