package net.hassannazar.common.repository;

import net.hassannazar.common.model.BaseEntity;

import java.util.List;

public interface CRUDRepository<T extends BaseEntity> {

    long create(T t);

    T read (final long id);

    List<T> readAll (final int offset, final int limit);

    long update (T t);

    void delete (final long id);

}
