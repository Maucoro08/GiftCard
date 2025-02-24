package com.mauricioCoronado.giftCard_auth_service.core.util.persistence;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Generic mapper interface for converting between entity and DTO.
 * @param <T> The entity type.
 * @param <K> The DTO type.
 */
public interface IBasePersistenceMapper <T,K>{
    K map(T input); // Maps an entity to a DTO
    T reverseMap(K input); // Maps a DTO to an entity
    // Reactive mappings for Flux and Mon
    default Mono<K> map(Mono<T> mono) {return mono.map(this::map);}
    default Flux<K> map(Flux<T> flux) {return flux.map(this::map);}
    default Mono<T> reverseMap(Mono<K> mono) {return mono.map(this::reverseMap);}
    default Flux<T> reverseMap(Flux<K> flux) {return flux.map(this::reverseMap);}
}
