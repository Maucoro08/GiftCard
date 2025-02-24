package com.mauricioCoronado.giftCard_manager.giftCard.infrastructure;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
@ActiveProfiles("test")
class GiftCardR2dbcServiceIntegrationTest {

    @Autowired
    private GiftCardR2dbcService service;

    @Autowired
    private IGiftCardR2dbcRepository repo;

    @BeforeAll
    static void setUp(@Autowired IGiftCardR2dbcRepository repo) {
        // Insertar datos iniciales
        GiftCardR2dbcEntity user1 = GiftCardR2dbcEntity.builder()
                .code("AAA-1")
                .amount(BigDecimal.valueOf(1000))
                .dateExpiration(LocalDate.now().plusMonths(1))
                .build();
        GiftCardR2dbcEntity user2 = GiftCardR2dbcEntity.builder()
                .code("AAA-2")
                .amount(BigDecimal.valueOf(1000))
                .dateExpiration(LocalDate.now().plusMonths(1))
                .build();
        repo.saveAll(Flux.just(user1, user2)).blockLast();
    }

    @Test
    void testFindAll() {
        // Act
        Flux<GiftCardR2dbcEntity> result = service.findAll();

        // Assert
        StepVerifier.create(result)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void testFindById() {
        // Act
        Mono<GiftCardR2dbcEntity> result = service.findById(1L);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(u -> u.getId().equals(1L) && u.getCode().equals("AAA-1"))
                .verifyComplete();
    }

    @Test
    void testSave() {
        // Arrange
        GiftCardR2dbcEntity entity = new GiftCardR2dbcEntity(null, "AAA-3", BigDecimal.valueOf(123456),null,LocalDate.now().plusMonths(3));

        // Act
        Mono<GiftCardR2dbcEntity> result = service.save(entity);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(u -> u.getId() != null && u.getCode().equals("AAA-3"))
                .verifyComplete();
    }

    @Test
    void testDeleteById() {
        // Act
        Mono<Boolean> result = service.deleteById(1L);

        // Assert
        StepVerifier.create(result)
                .expectNext(true)
                .verifyComplete();

        StepVerifier.create(repo.findById(1L))
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    void testFindByUsername() {
        // Act
        Mono<GiftCardR2dbcEntity> result = service.findByCode("AAA-1");

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(u -> u.getCode().equals("AAA-1"))
                .verifyComplete();
    }
}