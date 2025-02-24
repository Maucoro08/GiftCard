package com.mauricioCoronado.giftCard_auth_service.auth.infrastructure.value;

import com.mauricioCoronado.giftCard_auth_service.userSystem.domain.model.IUserSystem;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthUserSystem implements IUserSystem {

    private Long id;
    private String username;
    private String password;
}
