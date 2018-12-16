package uk.co.tatari.climb.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "room")
public class Room implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "room_id", columnDefinition = "integer")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="room_room_id_seq")
	@SequenceGenerator(name="room_room_id_seq", sequenceName="room_room_id_seq", allocationSize=1)
	private Integer roomId;	

	@NotNull
	@ManyToOne
	@JoinColumn(name = "centre_id")	
	private Centre centre;
    
	public Room() {
		super();
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Centre getCentre() {
		return centre;
	}

	public void setCentre(Centre centre) {
		this.centre = centre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roomId == null) ? 0 : roomId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (roomId == null) {
			if (other.roomId != null)
				return false;
		} else if (!roomId.equals(other.roomId))
			return false;
		return true;
	}

}
