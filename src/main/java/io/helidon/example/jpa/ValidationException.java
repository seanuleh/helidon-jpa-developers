package io.helidon.example.jpa;

public class ValidationException extends RuntimeException{

    public ValidationException(String message)
    {
        super(message);
    }
}
