package com.mauricioCoronado.giftCard_auth_service.userSystem.application.persistence;

import com.mauricioCoronado.giftCard_auth_service.core.util.persistence.IBasePersistenceService;
import com.mauricioCoronado.giftCard_auth_service.userSystem.domain.model.IUserSystem;
import com.mauricioCoronado.giftCard_auth_service.userSystem.domain.service.IPasswordEncoder;
import reactor.core.publisher.Mono;

public interface IUserSystemService extends IBasePersistenceService<IUserSystem,Long> {

    IPasswordEncoder getPasswordEncoder();

    @Override
    default Mono<IUserSystem> save(IUserSystem object) {
        String password = getPasswordEncoder().encode(object.getPassword());
        object.setPassword(password);
        return IBasePersistenceService.super.save(object);
    }
}
