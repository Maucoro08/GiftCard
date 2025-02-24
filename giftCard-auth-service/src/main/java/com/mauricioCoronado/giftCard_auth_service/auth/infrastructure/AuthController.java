package com.mauricioCoronado.giftCard_auth_service.auth.infrastructure;

import com.mauricioCoronado.giftCard_auth_service.auth.domain.model.IToken;
import com.mauricioCoronado.giftCard_auth_service.auth.infrastructure.service.AuthService;
import com.mauricioCoronado.giftCard_auth_service.userSystem.infrastructure.persistence.UserSystemCrudDto;
import com.mauricioCoronado.giftCard_auth_service.userSystem.infrastructure.persistence.UserSystemR2dbMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
