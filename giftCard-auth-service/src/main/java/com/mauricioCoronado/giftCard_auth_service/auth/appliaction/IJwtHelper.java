package com.mauricioCoronado.giftCard_auth_service.auth.appliaction;

import com.mauricioCoronado.giftCard_auth_service.auth.domain.model.IToken;

import java.time.LocalDateTime;

public interface IJwtHelper {
    String createToken(String username);
    IToken builderToken(String accessToken);
    boolean validateToken(String token);
    LocalDateTime getExpirationDate(String token);
}
