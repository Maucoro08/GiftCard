package com.mauricioCoronado.giftCard_auth_service.userSystem.application.persistence;


import com.mauricioCoronado.giftCard_auth_service.core.util.persistence.IBasePersistenceService;
import com.mauricioCoronado.giftCard_auth_service.core.util.persistence.ResourceNotFoundException;
import com.mauricioCoronado.giftCard_auth_service.userSystem.domain.model.IUserSystem;
import com.mauricioCoronado.giftCard_auth_service.userSystem.domain.persistence.repository.IUserSystemRepository;
import com.mauricioCoronado.giftCard_auth_service.userSystem.domain.service.IPasswordEncoder;
import reactor.core.publisher.Mono;

public interface IUserSystemService<T extends IUserSystem ,R extends IUserSystemRepository<T>> extends IBasePersistenceService<T,Long, R> {

    IPasswordEncoder getPasswordEncoder();

    @Override
    default Mono<T> save(T object) {
        return Mono.just(object)
                .flatMap(obj -> {
                    String encodedPassword = getPasswordEncoder().encode(obj.getPassword());
                    obj.setPassword(encodedPassword);
                    return IBasePersistenceService.super.save(obj);
                });
    }

    default Mono<T> findByUsername(String username) {
        return getRepo().findByUsername(username)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Resource not found with username " + username)));
    }
}
