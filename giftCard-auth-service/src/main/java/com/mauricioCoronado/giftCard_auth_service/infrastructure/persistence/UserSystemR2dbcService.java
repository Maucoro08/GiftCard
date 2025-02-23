package com.mauricioCoronado.giftCard_auth_service.infrastructure.persistence;

import com.mauricioCoronado.giftCard_auth_service.application.persistence.IUserSystemService;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserSystemR2dbcService implements IUserSystemService {

   private final IUserSystemR2dbcRepository repo;
}
