package uk.co.tatari.climb.repository;

import java.io.Serializable;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
interface BaseRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {
 
    <S extends T> S save(S entity) throws DataAccessException;
    T findOne(ID primaryKey);
    Iterable<T> findAll();
}