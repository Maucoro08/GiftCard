package com.mauricioCoronado.giftCard_auth_service.userSystem.domain.service;

public interface IPasswordEncoder {
    String encode(String pass);
    boolean matches(String source, String target);
}
