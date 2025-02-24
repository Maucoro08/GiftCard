package com.mauricioCoronado.giftCard_auth_service.userSystem.infrastructure.persistence;

import com.mauricioCoronado.giftCard_auth_service.userSystem.domain.persistence.repository.IUserSystemRepository;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserSystemR2dbcRepository extends IUserSystemRepository<UserSystemR2dbcEntity>, R2dbcRepository<UserSystemR2dbcEntity,Long> {
}
