package com.mauricioCoronado.giftCard_manager.giftCard.infrastructure.rabbitMQ.controller;

import com.mauricioCoronado.giftCard_manager.giftCard.infrastructure.persistence.GiftCardR2dbcService;
import com.mauricioCoronado.giftCard_manager.giftCard.infrastructure.rabbitMQ.service.GiftCardSendMessages;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Data
@RestController
@RequestMapping("/giftCard")
public class GiftCardRabbitMQController {

    private final GiftCardSendMessages giftCardSendMessages;


    @PostMapping("/{id}/issue")
    public Mono<ResponseEntity<Object>> sendGiftCardMessage(@PathVariable Long id) {
        return giftCardSendMessages.sendMessages(id)
                .then(Mono.just(ResponseEntity.ok().build()))
                .onErrorResume(e -> Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage())));
    }
}
