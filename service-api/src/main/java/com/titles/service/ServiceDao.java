package com.titles.service;

import java.util.List;
import java.util.Optional;


public interface ServiceDao<T> {

    List<T> findAll();

    Optional<T> findById(int id);

    /**
     * @param entity updating entity in database.
     * @return count of rows affected.
     */
    Integer update(T entity);

    /**
     * @param entity creating entity in database.
     * @return id of new entity generated by database.
     */
    Integer create(T entity);

    /**
     * @param id id of deleting entity.
     * @return count of rows affected.
     */
    Integer delete(int id);

    Integer count();
}
