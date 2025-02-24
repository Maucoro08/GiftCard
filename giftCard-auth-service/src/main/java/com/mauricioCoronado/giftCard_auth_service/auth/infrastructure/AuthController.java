package com.mauricioCoronado.giftCard_auth_service.auth.infrastructure;

import com.mauricioCoronado.giftCard_auth_service.auth.domain.model.IToken;
import com.mauricioCoronado.giftCard_auth_service.auth.infrastructure.service.AuthService;
import com.mauricioCoronado.giftCard_auth_service.auth.infrastructure.value.Token;
import com.mauricioCoronado.giftCard_auth_service.userSystem.infrastructure.persistence.UserSystemCrudDto;
import com.mauricioCoronado.giftCard_auth_service.userSystem.infrastructure.persistence.UserSystemR2dbMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;
    private UserSystemR2dbMapper mapper;

    @PostMapping(path = "login") //password secret
    public Mono<ResponseEntity<IToken>> jwtCreate(@RequestBody UserSystemCrudDto user) {
        return authService.login(mapper.reverseMap(user))
                .map(ResponseEntity::ok)
                .onErrorResume(Mono::error);
    }
    @PostMapping(path = "jwt")
    public Mono<ResponseEntity<IToken>> jwtValidate(@RequestHeader String accessToken) {
        return this.authService.validateToken(Token.builder().accessToken(accessToken).build())
                .map(tokenDto -> ResponseEntity.ok(tokenDto))
                .onErrorResume(ResponseStatusException.class, e -> {
                    return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized: " + e.getMessage()));
                });
    }
}
