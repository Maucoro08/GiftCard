package com.mauricioCoronado.giftCard_manager.giftCard.infrastructure.persistence;

import com.mauricioCoronado.giftCard_manager.core.util.persistence.IBasePersistenceMapper;
import org.springframework.stereotype.Component;

@Component
public class GiftCardR2dbMapper implements IBasePersistenceMapper<GiftCardR2dbcEntity, GiftCardCrudDto> {
    @Override
    public GiftCardCrudDto map(GiftCardR2dbcEntity input) {
        return GiftCardCrudDto.builder()
                .id(input.getId())
                .code(input.getCode())
                .amount(input.getAmount())
                .dateExpiration(input.getDateExpiration())
                .build();
    }

    @Override
    public GiftCardR2dbcEntity reverseMap(GiftCardCrudDto input) {
        return GiftCardR2dbcEntity.builder()
                .id(input.getId())
                .code(input.getCode())
                .amount(input.getAmount())
                .dateExpiration(input.getDateExpiration())
                .build();
    }
}
