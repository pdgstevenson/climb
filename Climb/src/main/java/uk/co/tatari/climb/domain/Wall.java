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
@Table(name = "wall")
@NamedEntityGraph(name="Wall.wallDetail", attributeNodes = {@NamedAttributeNode("screwThreads"), @NamedAttributeNode("naturalFeatures")})
public class Wall implements java.io.Serializable {

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
    
    @Column(name = "width_top", columnDefinition = "int")
	private Integer widthTop;  
    
    @Column(name = "height_left", columnDefinition = "int")
	private Integer heightLeft;  
    
    @Column(name = "height_right", columnDefinition = "int")
	private Integer heightRight; 
    
    @Column(name = "z_left", columnDefinition = "int")
	private Integer zLeft;  
    
    @Column(name = "z_right", columnDefinition = "int")
	private Integer zRight; 
    
    @Valid
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "wall")
    @OrderBy("x")
    private Set<ScrewThread> screwThreads = new LinkedHashSet<ScrewThread>(0);  
    
    @Valid
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "wall")
    @OrderBy("x")
    private Set<NaturalFeature> naturalFeatures = new LinkedHashSet<NaturalFeature>(0);    
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

	public Integer getWidthTop() {
		return widthTop;
	}

	public void setWidthTop(Integer widthTop) {
		this.widthTop = widthTop;
	}

	public Integer getHeightLeft() {
		return heightLeft;
	}

	public void setHeightLeft(Integer heightLeft) {
		this.heightLeft = heightLeft;
	}

	public Integer getHeightRight() {
		return heightRight;
	}

	public void setHeightRight(Integer heightRight) {
		this.heightRight = heightRight;
	}

	public Integer getzLeft() {
		return zLeft;
	}

	public void setzLeft(Integer zLeft) {
		this.zLeft = zLeft;
	}

	public Integer getzRight() {
		return zRight;
	}

	public void setzRight(Integer zRight) {
		this.zRight = zRight;
	}

	public Set<ScrewThread> getScrewThreads() {
		return screwThreads;
	}

	public void setScrewThreads(Set<ScrewThread> screwThreads) {
		this.screwThreads = screwThreads;
	}

	public Set<NaturalFeature> getNaturalFeatures() {
		return naturalFeatures;
	}

	public void setNaturalFeatures(Set<NaturalFeature> naturalFeatures) {
		this.naturalFeatures = naturalFeatures;
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
	
	public void addScrewThread(ScrewThread screwThread) {
		
		screwThreads.add(screwThread);
	}
}
