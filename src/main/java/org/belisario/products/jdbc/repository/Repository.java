package org.belisario.products.jdbc.repository;

import java.util.List;

public interface Repository<T> {

    List<T> findAll();

    T findById(Long id);

    void save(T t);

    void delet(Long id);

}
