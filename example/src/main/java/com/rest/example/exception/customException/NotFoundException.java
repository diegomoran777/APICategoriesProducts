package com.rest.example.exception.customException;

/**
 * Cuastom {@link NotFoundException} class
 */
public class NotFoundException extends Exception{
    
    public NotFoundException(String message) {
        super(message);
    }
}
