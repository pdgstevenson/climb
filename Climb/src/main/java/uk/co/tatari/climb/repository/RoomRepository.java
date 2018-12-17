package uk.co.tatari.climb.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import uk.co.tatari.climb.domain.Room;

public interface RoomRepository extends BaseRepository<Room, Integer> {
	
	@EntityGraph(value = "Room.wallDetail", type = EntityGraphType.LOAD)
	Room findByRoomId(Integer roomId) throws DataAccessException;
}
