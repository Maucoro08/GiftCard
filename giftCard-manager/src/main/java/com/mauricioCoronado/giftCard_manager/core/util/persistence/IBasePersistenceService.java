package com.mauricioCoronado.giftCard_manager.core.util.persistence;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Generic service interface for persistence operations, extending base repository functions.
 * Implements default methods using the IBaseRepository.
 * @param <T> The type of the entity, must implement IPersistable.
 * @param <ID> The type of the entity's identifier.
 */
public interface IBasePersistenceService<T extends IPersistable<ID>, ID, R extends IBaseRepository<T,ID>> {

    R getRepo(); // Provides repository implementation

    /**
     * Retrieves all entities.
     */
    default Flux<T> findAll() {
        return getRepo().findAll();
    }
    /**
     * Finds an entity by ID. Throws exception if not found.
     */
    default Mono<T> findById(ID id) {
        return getRepo().findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Resource not found with id " + id)));
    }
    /**
     * Saves a new entity.
     */
    default Mono<T> save(T object) {
        return getRepo().save(object);
    }
    /**
     * Updates an existing entity. If not found, throws an exception.
     */
    default Mono<T> update(ID id, T object) {
        return getRepo().findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Resource not found with id " + id)))
                .flatMap(existing -> {
                    object.setId(id);
                    return getRepo().save(object);
                });
    }
    /**
     * Deletes an entity by ID. Throws exception if not found.
     */
    default Mono<Boolean> deleteById(ID id) {
        return getRepo().findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Resource not found with id " + id)))
                .flatMap(existing -> getRepo().deleteById(id).thenReturn(true));
    }
}
