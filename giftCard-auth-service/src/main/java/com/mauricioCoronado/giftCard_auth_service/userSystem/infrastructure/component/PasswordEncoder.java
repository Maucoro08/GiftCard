package com.mauricioCoronado.giftCard_auth_service.userSystem.infrastructure.component;

import com.mauricioCoronado.giftCard_auth_service.userSystem.domain.service.IPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder implements IPasswordEncoder {

    private final BCryptPasswordEncoder passwordEncoder;

    public PasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encode(String pass) {
        return passwordEncoder.encode(pass);
    }

    @Override
    public boolean matches(String source, String target) {
        return passwordEncoder.matches(source,target);
    }
}
