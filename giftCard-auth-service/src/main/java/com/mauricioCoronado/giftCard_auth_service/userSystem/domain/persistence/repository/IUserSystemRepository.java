package com.mauricioCoronado.giftCard_auth_service.userSystem.domain.persistence.repository;

import com.mauricioCoronado.giftCard_auth_service.userSystem.domain.model.IUserSystem;
import com.mauricioCoronado.giftCard_auth_service.core.util.persistence.IBaseRepository;
import reactor.core.publisher.Mono;

public interface IUserSystemRepository<T extends  IUserSystem> extends IBaseRepository<T,Long> {

    Mono<T> findByUsername(String username);
}
