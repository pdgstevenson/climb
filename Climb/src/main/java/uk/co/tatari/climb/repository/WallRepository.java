package uk.co.tatari.climb.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import uk.co.tatari.climb.domain.Wall;

public interface WallRepository extends BaseRepository<Wall, Integer> {

	@EntityGraph(value = "Wall.wallDetail", type = EntityGraphType.LOAD)
	Wall findByWallId(Integer wallId) throws DataAccessException;
}
