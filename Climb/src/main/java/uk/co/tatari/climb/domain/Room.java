package uk.co.tatari.climb.domain;


import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "room")
@NamedEntityGraph(name="Room.wallDetail", attributeNodes = {@NamedAttributeNode("walls")})
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
	
	@Size(min = 1, max = 64)
    @Column(name = "name", columnDefinition = "text")
	private String name;  
	
    @Valid
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "room")
    @OrderBy("name")
    private Set<Wall> walls = new LinkedHashSet<Wall>(0);   
    
	public Room() {
		super();
	}

	public Room(Centre centre, String name) {
		super();
		this.centre = centre;
		this.name = name;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Centre getCentre() {
		return centre;
	}

	public void setCentre(Centre centre) {
		this.centre = centre;
	}

	public Set<Wall> getWalls() {
		return walls;
	}

	public void setWalls(Set<Wall> walls) {
		this.walls = walls;
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
