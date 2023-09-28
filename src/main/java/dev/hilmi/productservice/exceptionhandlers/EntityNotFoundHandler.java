package dev.hilmi.productservice.exceptionhandlers;

import dev.hilmi.productservice.dtos.GeneralOutput;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EntityNotFoundHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public GeneralOutput entityNotFoundAdvice(EntityNotFoundException e) {
        return new GeneralOutput(e.getMessage());
    }
}
