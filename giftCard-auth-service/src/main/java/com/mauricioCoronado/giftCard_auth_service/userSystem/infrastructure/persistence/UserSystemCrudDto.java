package com.mauricioCoronado.giftCard_auth_service.userSystem.infrastructure.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSystemCrudDto {
    private Long id;
    private String username;
    private String password;
}
