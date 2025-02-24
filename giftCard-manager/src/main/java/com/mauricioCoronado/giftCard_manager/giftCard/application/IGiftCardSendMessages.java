package com.mauricioCoronado.giftCard_manager.giftCard.application;

import com.mauricioCoronado.giftCard_manager.giftCard.domain.model.IGiftCard;
import reactor.core.publisher.Mono;

public interface IGiftCardSendMessages<ID> {

    Mono<Void> sendMessages(ID id);
}
