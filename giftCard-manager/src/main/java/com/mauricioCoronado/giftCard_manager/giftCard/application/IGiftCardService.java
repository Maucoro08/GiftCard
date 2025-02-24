package com.mauricioCoronado.giftCard_manager.giftCard.application;

import com.mauricioCoronado.giftCard_manager.core.util.persistence.IBasePersistenceService;
import com.mauricioCoronado.giftCard_manager.core.util.persistence.ResourceNotFoundException;
import com.mauricioCoronado.giftCard_manager.giftCard.domain.model.IGiftCard;
import com.mauricioCoronado.giftCard_manager.giftCard.domain.repository.IGiftCardRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface IGiftCardService <T extends IGiftCard,R extends IGiftCardRepository<T>> extends IBasePersistenceService<T,Long, R> {

    @Override
    default Mono<T> save(T object) {
        return Mono.just(object)
                .flatMap(obj -> {
                    if (obj.getId() == null || obj.getId() == 0) obj.setDateCreation(LocalDate.now()); //
                    return IBasePersistenceService.super.save(obj);
                });
    }

    default Mono<T> findByCode(String code) {
        return getRepo().findByCode(code)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Resource not found with code " + code)));
    }

}
