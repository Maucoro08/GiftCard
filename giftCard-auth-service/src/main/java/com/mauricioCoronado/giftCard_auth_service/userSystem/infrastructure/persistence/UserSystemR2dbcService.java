package com.mauricioCoronado.giftCard_auth_service.userSystem.infrastructure.persistence;

import com.mauricioCoronado.giftCard_auth_service.userSystem.application.persistence.IUserSystemService;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserSystemR2dbcService implements IUserSystemService {

   private final IUserSystemR2dbcRepository repo;
}
