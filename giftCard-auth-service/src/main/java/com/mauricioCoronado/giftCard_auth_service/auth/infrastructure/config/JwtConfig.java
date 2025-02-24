package com.mauricioCoronado.giftCard_auth_service.auth.infrastructure.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class JwtConfig {
    @Value("${application.jwt.secret:=n`vl+S3}|\"%U(PPn'YKn~VO<CQ}]b]dxjB/]PfcRNcp.+)IFN#7'u1<Vg,``B~kKxk_!eGAD{IzBmd%&_9a`v:tI)-b\":;faU}%QRRY)pn=trV8z$a^-XZ.0|f}~+?#}")
    private String secret;
    @Value("${application.jwt.expirationSeconds:3600}")
    private Long expirationSeconds;

}
