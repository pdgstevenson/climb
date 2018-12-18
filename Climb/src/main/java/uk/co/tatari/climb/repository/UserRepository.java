package uk.co.tatari.climb.repository;


import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import uk.co.tatari.climb.domain.UserEntity;


public interface UserRepository extends BaseRepository<UserEntity, Integer> {
	
	public UserEntity findByUsername(String username) throws DataAccessException;
	public Page<UserEntity> findAllByOrderByUsername(Pageable pageable) throws DataAccessException;
	public List<UserEntity> findAllByOrderByUsername() throws DataAccessException;
}
