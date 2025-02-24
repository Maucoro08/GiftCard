package com.mauricioCoronado.giftCard_manager.core.util.persistence;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Generic REST controller interface providing common CRUD endpoints.
 * Uses IBasePersistenceService for business logic and IBasePersistenceMapper for DTO conversion.
 * @param <T> Entity type, must implement IPersistable.
 * @param <ID> Type of the entity's identifier.
 * @param <DTO> DTO type for API communication.
 */
@RestController
public interface IBasePersistenceController<T extends IPersistable<ID>
        , ID
        , R extends IBaseRepository<T,ID>
        , S extends IBasePersistenceService<T, ID, R>
        , DTO
        , M extends IBasePersistenceMapper<T, DTO>> {
    S getService(); // Provides access to service layer
    M getMapper(); // Provides access to mapper

    /**
     * Retrieves all entities as DTOs.
     */
    @GetMapping("/")
    default Mono<ResponseEntity<Flux<DTO>>> getAll() {
        Flux<DTO> dtoFlux = getService().findAll()
                .map(entity -> getMapper().map(entity));
        return Mono.just(ResponseEntity.ok(dtoFlux));
    }
    /**
     * Retrieves an entity by ID, returns 404 if not found.
     */
    @GetMapping("/{id}")
    default Mono<ResponseEntity<DTO>> getById(@PathVariable("id") ID id) {
        return getService().findById(id)
                .map(entity -> ResponseEntity.ok(getMapper().map(entity)))
                .onErrorResume(ResourceNotFoundException.class,
                        e -> Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }
    /**
     * Creates a new entity from DTO and returns the created entity.
     */
    @PostMapping("/")
    default Mono<ResponseEntity<DTO>> create(@RequestBody DTO dto) {
        return getService().save(getMapper().reverseMap(dto))
                .map(savedEntity -> ResponseEntity.status(HttpStatus.CREATED).body(getMapper().map(savedEntity)));
    }
    /**
     * Updates an existing entity, returns 400 if not found.
     */
    @PutMapping("/{id}")
    default Mono<ResponseEntity<DTO>> update(@PathVariable("id") ID id, @RequestBody DTO dto) {
        return getService().update(id, getMapper().reverseMap(dto))
                .map(updatedEntity -> ResponseEntity.ok(getMapper().map(updatedEntity)))
                .onErrorResume(ResourceNotFoundException.class,
                        e -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).build()));
    }
    /**
     * Deletes an entity by ID, returns appropriate response.
     */
    @DeleteMapping("/{id}")
    default Mono<ResponseEntity<Void>> delete(@PathVariable("id") ID id) {
        return getService().deleteById(id)
                .map(deleted -> ResponseEntity.noContent().<Void>build())
                .onErrorResume(ResourceNotFoundException.class,
                        e -> Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).<Void>build()))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().<Void>build()));
    }
}

