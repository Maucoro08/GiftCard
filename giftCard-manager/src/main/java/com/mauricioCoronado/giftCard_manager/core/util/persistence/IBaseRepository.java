package com.mauricioCoronado.giftCard_manager.core.util.persistence;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Generic repository interface for CRUD operations using Project Reactor.
 * @param <T> The type of the entity.
 * @param <ID> The type of the entity's identifier.
 */
public interface IBaseRepository<T,ID>{

    Flux<T> findAll(); // Retrieves all entities
    Mono<T> findById(ID id); // Finds an entity by ID
    <S extends T> Mono<S> save(S object); // Saves a new entity
    Mono<Void> deleteById(ID id); // Deletes an entity by ID
}
