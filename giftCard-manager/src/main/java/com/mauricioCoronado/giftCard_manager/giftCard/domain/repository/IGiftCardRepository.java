package com.mauricioCoronado.giftCard_manager.giftCard.domain.repository;

import com.mauricioCoronado.giftCard_manager.core.util.persistence.IBaseRepository;
import com.mauricioCoronado.giftCard_manager.giftCard.domain.model.IGiftCard;
import reactor.core.publisher.Mono;

public interface IGiftCardRepository <T extends IGiftCard> extends IBaseRepository<T,Long> {

    Mono<T> findByCode(String code);
}
