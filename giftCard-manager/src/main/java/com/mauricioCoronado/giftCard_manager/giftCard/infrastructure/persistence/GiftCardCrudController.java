package com.mauricioCoronado.giftCard_manager.giftCard.infrastructure.persistence;

import com.mauricioCoronado.giftCard_manager.core.util.persistence.IBasePersistenceController;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
@RequestMapping("/giftCard")
public class GiftCardCrudController implements IBasePersistenceController<GiftCardR2dbcEntity
        , Long
        , IGiftCardR2dbcRepository
        , GiftCardR2dbcService
        , GiftCardCrudDto
        , GiftCardR2dbMapper> {

    private final GiftCardR2dbcService service;
    private final GiftCardR2dbMapper mapper;
}
