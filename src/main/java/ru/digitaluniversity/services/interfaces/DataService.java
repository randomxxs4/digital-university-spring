package ru.digitaluniversity.services.interfaces;

import org.springframework.data.domain.Page;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;

import java.util.Optional;


/**
 * The interface services.
 *
 * @param <T> the type parameter
 */
public interface DataService<T> {
    /**
     * Find all data
     *
     * @param page the page
     * @param size the size
     * @return the page
     */
    Page<T> findAll(Optional<Integer> page, Optional<Integer> size);

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
