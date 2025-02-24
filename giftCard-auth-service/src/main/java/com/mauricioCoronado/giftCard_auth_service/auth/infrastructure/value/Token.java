package com.mauricioCoronado.giftCard_auth_service.auth.infrastructure.value;

import com.mauricioCoronado.giftCard_auth_service.auth.domain.model.IToken;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Token implements IToken {

    String accessToken;
}
