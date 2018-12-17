package uk.co.tatari.climb.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import uk.co.tatari.climb.domain.Centre;

public interface CentreRepository extends BaseRepository<Centre, Integer> {
	
	@EntityGraph(value = "Centre.roomDetail", type = EntityGraphType.LOAD)
	Centre findByCentreId(Integer climbId) throws DataAccessException;
}
