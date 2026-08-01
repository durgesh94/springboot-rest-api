package com.learning.springboot.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Long id) {
        super("User not found with id : " + id);
    }
}
