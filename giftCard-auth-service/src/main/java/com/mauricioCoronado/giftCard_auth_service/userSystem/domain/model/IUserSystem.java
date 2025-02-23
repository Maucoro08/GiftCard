package com.mauricioCoronado.giftCard_auth_service.userSystem.domain.model;

import com.mauricioCoronado.giftCard_auth_service.core.util.persistence.IPersistable;

public interface IUserSystem extends IPersistable<Long> {
    String getUsername();
    String getPassword();
}
