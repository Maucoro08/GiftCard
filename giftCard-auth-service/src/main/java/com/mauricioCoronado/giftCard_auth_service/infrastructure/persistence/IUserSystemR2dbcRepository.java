package com.mauricioCoronado.giftCard_auth_service.infrastructure.persistence;

import com.mauricioCoronado.giftCard_auth_service.core.util.persistence.IBaseRepository;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserSystemR2dbcRepository extends IBaseRepository<UserSystemR2dbcEntity,Long>,
                                                    R2dbcRepository<UserSystemR2dbcEntity,Long> {
}
