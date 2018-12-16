package uk.co.tatari.climb.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
	

	@Size(min = 1, max = 512)
    @Column(name = "description", columnDefinition = "text")
	private String description;  

    @Column(name = "width", columnDefinition = "int")
	private Integer width;  
    
	public Wall() {
		super();
	}

	public Integer getWallId() {
		return wallId;
	}

	public void setWallId(Integer wallId) {
		this.wallId = wallId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
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
