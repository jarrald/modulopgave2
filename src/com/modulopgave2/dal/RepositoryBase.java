package com.modulopgave2.dal;

import java.util.Collection;

public abstract class RepositoryBase<T> implements Repository<T> {
    @Override
    public T create(T entity) throws Exception {
        throw new Exception("Method not Implemented..");
    }

    @Override
    public T read(T entity) throws Exception {
        throw new Exception("Method not Implemented..");
    }

    @Override
    public void update(T entity) throws Exception {
        throw new Exception("Method not Implemented..");
    }

    @Override
    public void delete(T entity) throws Exception {
        throw new Exception("Method not Implemented..");
    }

    @Override
    public Collection<T> list() throws Exception {
        throw new Exception("Method not Implemented..");
    }

    @Override
    public Collection<T> find(T entity) throws Exception {
        throw new Exception("Method not Implemented..");
    }
}
