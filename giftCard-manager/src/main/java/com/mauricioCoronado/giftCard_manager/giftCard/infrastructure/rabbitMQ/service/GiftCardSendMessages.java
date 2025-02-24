package com.mauricioCoronado.giftCard_manager.giftCard.infrastructure.rabbitMQ.service;


import com.mauricioCoronado.giftCard_manager.giftCard.application.IGiftCardSendMessages;
import com.mauricioCoronado.giftCard_manager.giftCard.domain.model.IGiftCard;
import com.mauricioCoronado.giftCard_manager.giftCard.infrastructure.persistence.GiftCardR2dbcService;
import lombok.Data;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
@EnableRabbit
@Data
public class GiftCardSendMessages implements IGiftCardSendMessages<Long> {

    private final GiftCardR2dbcService servicePersistence;
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    @Override
    public Mono<Void> sendMessages(Long id) {
        return servicePersistence.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("GiftCard no encontrado con id: " + id)))
                .flatMap(giftCard -> Mono.defer(() -> {
                    rabbitTemplate.convertAndSend(queue.getName(), giftCard);
                    return Mono.empty(); // Indica que la operación es void
                }))
                .then();
    }
}
