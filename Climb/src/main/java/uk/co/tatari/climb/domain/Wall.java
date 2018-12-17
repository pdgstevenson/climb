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
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Entity
@Table(name = "wall")
public class Wall implements java.io.Serializable {

	private static Logger LOGGER = LoggerFactory.getLogger(Wall.class);
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "wall_id", columnDefinition = "integer")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="wall_wall_id_seq")
	@SequenceGenerator(name="wall_wall_id_seq", sequenceName="wall_wall_id_seq", allocationSize=1)
	private Integer wallId;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "room_id")	
	private Room room;
	
    @Column(name = "num", columnDefinition = "smallint")
	private Integer num;  
	
	@Size(min = 0, max = 32)
    @Column(name = "orientation", columnDefinition = "text")
	private String orientation;  
	
	@Size(min = 1, max = 64)
    @Column(name = "name", columnDefinition = "text")
	private String name;  
	
	@Size(min = 1, max = 512)
    @Column(name = "description", columnDefinition = "text")
	private String description;  

    @Column(name = "width_base", columnDefinition = "int")
	private Integer widthBase;  
    
	public Wall() {
		super();
	}

	public Wall(Room room, Integer num, String orientation) {
		super();
		this.room = room;
		this.num = num;
		this.orientation = orientation;
	}

	public Integer getWallId() {
		return wallId;
	}

	public void setWallId(Integer wallId) {
		this.wallId = wallId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Integer getWidthBase() {
		return widthBase;
	}

	public void setWidthBase(Integer widthBase) {
		this.widthBase = widthBase;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((wallId == null) ? 0 : wallId.hashCode());
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
		Wall other = (Wall) obj;
		if (wallId == null) {
			if (other.wallId != null)
				return false;
		} else if (!wallId.equals(other.wallId))
			return false;
		return true;
	}
}
