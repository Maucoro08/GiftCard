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
        String password = getPasswordEncoder().encode(object.getPassword());
        object.setPassword(password);
        return IBasePersistenceService.super.save(object);
    }

    default Mono<T> findByUsername(String username) {
        return getRepo().findByUsername(username)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Resource not found with username " + username)));
    }
}
