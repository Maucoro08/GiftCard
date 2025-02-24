package com.mauricioCoronado.giftCard_manager.core.util.persistence;

/**
 * Interface for persistable entities.
 * @param <ID> The type of the entity's identifier.
 */
public interface IPersistable <ID>{
    ID getId();
    void setId(ID id);
}
