package com.mauricioCoronado.giftCard_auth_service.auth.infrastructure.service;

import com.mauricioCoronado.giftCard_auth_service.auth.appliaction.IJwtHelper;
import com.mauricioCoronado.giftCard_auth_service.auth.domain.model.IToken;
import com.mauricioCoronado.giftCard_auth_service.auth.infrastructure.config.JwtConfig;
import com.mauricioCoronado.giftCard_auth_service.auth.infrastructure.value.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtHelper implements IJwtHelper {

    private final JwtConfig jwtConfig;

    public JwtHelper(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    public String createToken(String username) {
        final var now = new Date();
        final var expirationDate = new Date(now.getTime() + (jwtConfig.getExpirationSeconds() * 1000));
        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expirationDate)
                .signWith(this.getSecretKey())
                .compact();
    }

    @Override
    public IToken builderToken(String accessToken) {
        return Token.builder().accessToken(accessToken).build();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            final LocalDateTime expirationDate = this.getExpirationDate(token);
            return expirationDate.isAfter(LocalDateTime.now());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Jwt invalid");
        }
    }

    @Override
    public LocalDateTime getExpirationDate(String token) {
        return toLocalDateTime(this.getClaimsFromToken(token, Claims::getExpiration));
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8));
    }

    private <T> T getClaimsFromToken(String token, Function<Claims, T> resolver) {
        return resolver.apply(this.signToken(token));
    }

    private Claims signToken(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(this.getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}
