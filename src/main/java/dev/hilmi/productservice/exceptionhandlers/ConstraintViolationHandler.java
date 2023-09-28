package dev.hilmi.productservice.exceptionhandlers;

import dev.hilmi.productservice.dtos.GeneralOutput;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ConstraintViolationHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public GeneralOutput constraintViolationAdvice(ConstraintViolationException e) {
        return new GeneralOutput(e.getMessage());
    }
}
