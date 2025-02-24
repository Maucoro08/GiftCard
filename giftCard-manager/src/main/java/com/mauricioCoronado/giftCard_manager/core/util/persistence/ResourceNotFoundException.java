package com.mauricioCoronado.giftCard_manager.core.util.persistence;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
