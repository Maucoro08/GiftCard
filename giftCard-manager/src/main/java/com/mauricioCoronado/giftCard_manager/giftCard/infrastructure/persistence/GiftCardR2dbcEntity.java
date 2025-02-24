package com.mauricioCoronado.giftCard_manager.giftCard.infrastructure.persistence;

import com.mauricioCoronado.giftCard_manager.giftCard.domain.model.IGiftCard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("users_system")
public class GiftCardR2dbcEntity implements IGiftCard {

    @Id
    private Long id;
    private String code;
    private BigDecimal amount;
    private LocalDate dateCreation;
    private LocalDate dateExpiration;
}
