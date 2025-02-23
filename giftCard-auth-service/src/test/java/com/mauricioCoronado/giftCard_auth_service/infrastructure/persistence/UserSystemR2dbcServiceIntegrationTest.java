package com.mauricioCoronado.giftCard_auth_service.infrastructure.persistence;
import com.mauricioCoronado.giftCard_auth_service.domain.model.IUserSystem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@ActiveProfiles("test")
class UserSystemR2dbcServiceIntegrationTest {

    @Autowired
    private UserSystemR2dbcService service;

    @Autowired
    private IUserSystemR2dbcRepository repo;

    @BeforeAll
    static void setUp(@Autowired IUserSystemR2dbcRepository repo) {
        // Insertar datos iniciales
        UserSystemR2dbcEntity user1 = UserSystemR2dbcEntity.builder()
                .username("user1")
                .password("pass1")
                .build();
        UserSystemR2dbcEntity user2 = UserSystemR2dbcEntity.builder()
                .username("user2")
                .password("pass2")
                .build();
        repo.saveAll(Flux.just(user1, user2)).blockLast();
    }


    @Test
    void testFindAll() {
        // Act
        Flux<IUserSystem> result = service.findAll();

        // Assert
        StepVerifier.create(result)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void testFindById() {
        // Act
        Mono<IUserSystem> result = service.findById(1L);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(u -> u.getId().equals(1L) && u.getUsername().equals("user1"))
                .verifyComplete();
    }

    @Test
    void testSave() {
        // Arrange
        UserSystemR2dbcEntity user = new UserSystemR2dbcEntity(null, "user3", "pass3");

        // Act
        Mono<IUserSystem> result = service.save(user);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(u -> u.getId() != null && u.getUsername().equals("user3"))
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
}