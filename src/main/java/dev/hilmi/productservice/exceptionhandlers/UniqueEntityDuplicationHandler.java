package dev.hilmi.productservice.exceptionhandlers;

import dev.hilmi.productservice.dtos.GeneralOutput;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UniqueEntityDuplicationHandler {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(UniqueEntityDuplicationException.class)
    GeneralOutput uniqueEntityDuplicationAdvice(UniqueEntityDuplicationException e) {
        return new GeneralOutput(e.getMessage());
    }
}
