package com.modulopgave2.dal;

import java.util.Collection;

public interface Repository<T> {
    T create(T entity) throws Exception;
    T read(T entity) throws Exception;
    void update(T entity) throws Exception;
    void delete(T entity) throws Exception;
    Collection<T> list() throws Exception;
    Collection<T> find(T entity) throws Exception;
}
