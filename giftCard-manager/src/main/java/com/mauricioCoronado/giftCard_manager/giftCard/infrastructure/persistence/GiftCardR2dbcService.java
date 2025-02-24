package com.mauricioCoronado.giftCard_manager.giftCard.infrastructure.persistence;

import com.mauricioCoronado.giftCard_manager.giftCard.application.IGiftCardService;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class GiftCardR2dbcService implements IGiftCardService<GiftCardR2dbcEntity, IGiftCardR2dbcRepository> {

   private final IGiftCardR2dbcRepository repo;
}
