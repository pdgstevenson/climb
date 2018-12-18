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
@Table(name = "natural_feature")
public class NaturalFeature implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "natural_feature_id", columnDefinition = "integer")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="natural_feature_natural_feature_id_seq")
	@SequenceGenerator(name="natural_feature_natural_feature_id_seq", sequenceName="natural_feature_natural_feature_id_seq", allocationSize=1)
	private Integer naturalFeatureId;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "wall_id")	
	private Wall wall;
	
	@NotNull
    @Column(name = "x", columnDefinition = "int")
	private Integer x;  
	
	@NotNull
    @Column(name = "y", columnDefinition = "int")
	private Integer y;  
	
	@NotNull
    @Column(name = "z", columnDefinition = "int")
	private Integer z;  
    
	public NaturalFeature() {
		super();
	}

	public NaturalFeature(Wall wall, Integer x, Integer y, Integer z) {
		super();
		this.wall = wall;
		this.x = x;
		this.y = y;
		this.z = z;
	}


	public Integer getNaturalFeatureId() {
		return naturalFeatureId;
	}

	public void setNaturalFeatureId(Integer naturalFeatureId) {
		this.naturalFeatureId = naturalFeatureId;
	}

	public Wall getWall() {
		return wall;
	}

	public void setWall(Wall wall) {
		this.wall = wall;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getZ() {
		return z;
	}

	public void setZ(Integer z) {
		this.z = z;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naturalFeatureId == null) ? 0 : naturalFeatureId.hashCode());
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
		NaturalFeature other = (NaturalFeature) obj;
		if (naturalFeatureId == null) {
			if (other.naturalFeatureId != null)
				return false;
		} else if (!naturalFeatureId.equals(other.naturalFeatureId))
			return false;
		return true;
	}

}
