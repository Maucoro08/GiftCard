package com.mauricioCoronado.giftCard_auth_service.userSystem.infrastructure.persistence;

import com.mauricioCoronado.giftCard_auth_service.core.util.persistence.IBasePersistenceMapper;
import org.springframework.stereotype.Component;

@Component
public class UserSystemR2dbMapper implements IBasePersistenceMapper <UserSystemR2dbcEntity,UserSystemCrudDto> {
    @Override
    public UserSystemCrudDto map(UserSystemR2dbcEntity input) {
        return UserSystemCrudDto.builder()
                .id(input.getId())
                .username(input.getUsername())
                .password(input.getPassword())
                .build();
    }

    @Override
    public UserSystemR2dbcEntity reverseMap(UserSystemCrudDto input) {
        return UserSystemR2dbcEntity.builder()
                .id(input.getId())
                .username(input.getUsername())
                .password(input.getPassword())
                .build();
    }
}
