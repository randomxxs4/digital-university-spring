package ru.digitaluniversity.converter;

import ru.digitaluniversity.exception.ConvertException;


/**
 * The interface for converting an entity to a DTO.
 *
 * @param <T> entity type
 * @param <S> object of type DTO
 */
public interface Converter<T, S> {


    /**
     * Converts from the entity class to the DTO class
     *
     * @param obj - entity type
     * @return the s - object of type DTO
     */
    S convertToDto(T obj);

    /**
     * Converts from the DTO class to the entity class
     *
     * @param obj - DTO type
     * @return the s - Entity type
     * @throws ConvertException the convert exception
     */
    T convertToEntity(S obj);

}
