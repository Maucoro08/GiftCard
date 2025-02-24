package com.mauricioCoronado.giftCard_auth_service.auth.appliaction;

import com.mauricioCoronado.giftCard_auth_service.auth.domain.model.IToken;
import com.mauricioCoronado.giftCard_auth_service.core.util.persistence.ResourceNotFoundException;
import com.mauricioCoronado.giftCard_auth_service.userSystem.application.persistence.IUserSystemService;
import com.mauricioCoronado.giftCard_auth_service.userSystem.domain.model.IUserSystem;
import com.mauricioCoronado.giftCard_auth_service.userSystem.domain.persistence.repository.IUserSystemRepository;
import com.mauricioCoronado.giftCard_auth_service.userSystem.domain.service.IPasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

public interface IAuthService<T extends IUserSystem ,R extends IUserSystemRepository<T>> {

    String USER_EXCEPTION_MSG = "Error to auth user";

    IUserSystemService<T,R> getUserSystemPersistence();
    IPasswordEncoder getPasswordEncoder();
    IJwtHelper getJwtHelper();

    default Mono<IToken> login(T userSystem) {
        return this.getUserSystemPersistence().findByUsername(userSystem.getUsername())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, USER_EXCEPTION_MSG)))
                .flatMap(userFromDB -> {
                    this.validPassword(userSystem, userFromDB);
                    String accessToken = getJwtHelper().createToken(userFromDB.getUsername());
                    return Mono.just(getJwtHelper().builderToken(accessToken));
                })
                .onErrorResume(ResourceNotFoundException.class, e -> {
                    return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, USER_EXCEPTION_MSG));
                })
                .onErrorResume(ResponseStatusException.class, Mono::error);
    }
    default void validPassword(T source, T target) {
        if (!getPasswordEncoder().matches(source.getPassword(), target.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, USER_EXCEPTION_MSG);
        }
    }

}
