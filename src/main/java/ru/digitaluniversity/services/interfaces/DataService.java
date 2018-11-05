package ru.digitaluniversity.services.interfaces;

import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;

import java.util.List;


/**
 * The interface services.
 *
 * @param <T> the type parameter
 */
public interface DataService<T> {
    List<T> findAll();

    /**
     * Find data by id
     *
     * @param id the id
     * @return the t
     */
    T findById(Integer id) throws ConvertException, NotFoundException;

    /**
     * Create data
     *
     * @param obj the obj
     * @return the t
     */
    T create(T obj);
}
