package dev.hilmi.productservice.exceptionhandlers;

public class UniqueEntityDuplicationException extends RuntimeException {

    public UniqueEntityDuplicationException(String message) {
        super(message);
    }
}
