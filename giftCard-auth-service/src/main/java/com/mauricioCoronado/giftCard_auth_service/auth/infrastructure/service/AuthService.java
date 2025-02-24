package com.mauricioCoronado.giftCard_auth_service.auth.infrastructure.service;

import com.mauricioCoronado.giftCard_auth_service.auth.appliaction.IAuthService;
import com.mauricioCoronado.giftCard_auth_service.userSystem.infrastructure.component.PasswordEncoder;
import com.mauricioCoronado.giftCard_auth_service.userSystem.infrastructure.persistence.IUserSystemR2dbcRepository;
import com.mauricioCoronado.giftCard_auth_service.userSystem.infrastructure.persistence.UserSystemR2dbcEntity;
import com.mauricioCoronado.giftCard_auth_service.userSystem.infrastructure.persistence.UserSystemR2dbcService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@Service
public class AuthService implements IAuthService<UserSystemR2dbcEntity, IUserSystemR2dbcRepository> {

    private final UserSystemR2dbcService userSystemPersistence;
    private final PasswordEncoder passwordEncoder;
    private final JwtHelper jwtHelper;

}
