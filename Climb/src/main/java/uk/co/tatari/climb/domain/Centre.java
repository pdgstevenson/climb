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
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Size;

@Entity
@Table(name = "centre")
@NamedEntityGraph(name="Centre.roomDetail", attributeNodes = {@NamedAttributeNode("rooms")})
public class Centre implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "centre_id", columnDefinition = "integer")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="centre_centre_id_seq")
	@SequenceGenerator(name="centre_centre_id_seq", sequenceName="centre_centre_id_seq", allocationSize=1)
	private Integer centreId;
	
	@Size(min = 1, max = 64)
    @Column(name = "name", columnDefinition = "text")
	private String name;  
	
    @Valid
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "centre")
    @OrderBy("name")
    private Set<Room> rooms = new LinkedHashSet<Room>(0);   
	
	public Centre() {
		super();
	}

	public Centre(String name) {
		super();
		this.name = name;
	}

	public Integer getCentreId() {
		return centreId;
	}

	public void setCentreId(Integer centreId) {
		this.centreId = centreId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((centreId == null) ? 0 : centreId.hashCode());
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
		Centre other = (Centre) obj;
		if (centreId == null) {
			if (other.centreId != null)
				return false;
		} else if (!centreId.equals(other.centreId))
			return false;
		return true;
	}
	
	public void addRoom(Room room) {
		
		rooms.add(room);
	}

}
