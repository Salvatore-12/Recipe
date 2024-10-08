package Salvo_assenato.Recipe.exceptions;

import Salvo_assenato.Recipe.payloads.ErrorPayloadsList;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
@RestControllerAdvice
public class ExceptionHandler {

    //400
    @org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorPayloadsList handleBadRequest(BadRequestException e) {
        List<String> errorsMessages = new ArrayList<>();
        if (e.getErrorList() != null)
            errorsMessages = e.getErrorList().stream().map(err -> err.getDefaultMessage()).toList();
        return new ErrorPayloadsList(e.getMessage(), errorsMessages);
    }

    //401
    @org.springframework.web.bind.annotation.ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorsPayload handleUnauthorized(UnauthorizedException ex) {
        return new ErrorsPayload(ex.getMessage());
    }

    // 403
    @org.springframework.web.bind.annotation.ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorsPayload handleAccessDenied(AccessDeniedException ex) {
        return new ErrorsPayload("Il tuo ruolo non permette di accedere a questa funzionalità!");
    }

    //404
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsPayload handleNotFoundExc(NotFoundException ex) {
        return new ErrorsPayload(ex.getMessage());
    }

    //500 family
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsPayload handleGenericError(Exception ex) {
        ex.printStackTrace();
        return new ErrorsPayload("Problema lato server.");

    }
}
