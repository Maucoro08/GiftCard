package com.mauricioCoronado.giftCard_manager.giftCard.infrastructure;

import com.mauricioCoronado.giftCard_manager.giftCard.domain.repository.IGiftCardRepository;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGiftCardR2dbcRepository extends IGiftCardRepository<GiftCardR2dbcEntity>, R2dbcRepository<GiftCardR2dbcEntity,Long> {
}
