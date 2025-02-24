package com.mauricioCoronado.giftCard_manager.giftCard.domain.model;

import com.mauricioCoronado.giftCard_manager.core.util.persistence.IPersistable;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface IGiftCard extends IPersistable<Long> {
    String getCode();
    BigDecimal getAmount();
    LocalDate getDateCreation();
    LocalDate getDateExpiration();
}
