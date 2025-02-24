package com.mauricioCoronado.giftCard_auth_service.userSystem.infrastructure.persistence;

import com.mauricioCoronado.giftCard_auth_service.core.util.persistence.IBasePersistenceController;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
@RequestMapping("/userSystem")
public class UserSystemCrudController implements IBasePersistenceController<UserSystemR2dbcEntity
        , Long
        , IUserSystemR2dbcRepository
        , UserSystemR2dbcService
        , UserSystemCrudDto
        , UserSystemR2dbMapper> {

    private final UserSystemR2dbcService service;
    private final UserSystemR2dbMapper mapper;
}
