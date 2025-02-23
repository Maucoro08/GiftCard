package com.mauricioCoronado.giftCard_auth_service.core.util.persistence;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
