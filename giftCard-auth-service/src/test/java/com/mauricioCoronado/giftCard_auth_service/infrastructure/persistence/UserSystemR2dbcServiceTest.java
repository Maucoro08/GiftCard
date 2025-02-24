package com.mauricioCoronado.giftCard_auth_service.infrastructure.persistence;

import com.mauricioCoronado.giftCard_auth_service.userSystem.domain.model.IUserSystem;
import com.mauricioCoronado.giftCard_auth_service.userSystem.infrastructure.persistence.IUserSystemR2dbcRepository;
import com.mauricioCoronado.giftCard_auth_service.userSystem.infrastructure.persistence.UserSystemR2dbcEntity;
import com.mauricioCoronado.giftCard_auth_service.userSystem.infrastructure.persistence.UserSystemR2dbcService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;


class UserSystemR2dbcServiceTest {

    @Mock
    private IUserSystemR2dbcRepository repo;

    @InjectMocks
    private UserSystemR2dbcService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    void testFindAll() {
        // Arrange
        UserSystemR2dbcEntity user1 = new UserSystemR2dbcEntity(1L, "user1", "pass1");
        UserSystemR2dbcEntity user2 = new UserSystemR2dbcEntity(2L, "user2", "pass2");
        when(repo.findAll()).thenReturn(Flux.just(user1, user2));

        // Act
        Flux<UserSystemR2dbcEntity> result = service.findAll();

        // Assert
        StepVerifier.create(result)
                .expectNext(user1)
                .expectNext(user2)
                .verifyComplete();

        verify(repo, times(1)).findAll();
    }

    void testFindById() {
        // Arrange
        Long id = 1L;
        UserSystemR2dbcEntity user = new UserSystemR2dbcEntity(id, "user1", "pass1");
        when(repo.findById(id)).thenReturn(Mono.just(user));

        // Act
        Mono<UserSystemR2dbcEntity> result = service.findById(id);

        // Assert
        StepVerifier.create(result)
                .expectNext(user)
                .verifyComplete();

        verify(repo, times(1)).findById(id);
    }

    void testSave() {
        // Arrange
        UserSystemR2dbcEntity user = new UserSystemR2dbcEntity(null, "user1", "pass1");
        UserSystemR2dbcEntity savedUser = new UserSystemR2dbcEntity(1L, "user1", "pass1");
        when(repo.save(user)).thenReturn(Mono.just(savedUser));

        // Act
        Mono<UserSystemR2dbcEntity> result = service.save(user);

        // Assert
        StepVerifier.create(result)
                .expectNext(savedUser)
                .verifyComplete();

        verify(repo, times(1)).save(user);
    }

    void testDeleteById() {
        // Arrange
        Long id = 1L;
        UserSystemR2dbcEntity user = new UserSystemR2dbcEntity(id, "user1", "pass1");
        when(repo.findById(id)).thenReturn(Mono.just(user));
        when(repo.deleteById(id)).thenReturn(Mono.empty());

        // Act
        Mono<Boolean> result = service.deleteById(id);

        // Assert
        StepVerifier.create(result)
                .expectNext(true)
                .verifyComplete();

        verify(repo, times(1)).findById(id);
        verify(repo, times(1)).deleteById(id);
    }
}